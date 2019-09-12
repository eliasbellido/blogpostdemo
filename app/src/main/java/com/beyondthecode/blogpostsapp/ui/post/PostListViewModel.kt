package com.beyondthecode.blogpostsapp.ui.post

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.beyondthecode.blogpostsapp.R
import com.beyondthecode.blogpostsapp.base.BaseViewModel
import com.beyondthecode.blogpostsapp.model.Post
import com.beyondthecode.blogpostsapp.model.PostDao
import com.beyondthecode.blogpostsapp.network.PostApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostListViewModel(private val postDao: PostDao): BaseViewModel() {

    @Inject
    lateinit var postApi: PostApi
    
    private lateinit var subscription: Disposable

    val postListAdapter: PostListAdapter = PostListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClicklistener = View.OnClickListener { loadPosts() }

    init{
        loadPosts()
    }

    private fun loadPosts(){
        subscription = Observable.fromCallable { postDao.all }
            .concatMap {
                dbPostList -> if(dbPostList.isEmpty()){
                                postApi.getPosts().concatMap {
                                    apiPostList -> postDao.insertAll(*apiPostList.toTypedArray())
                                    Observable.just(apiPostList)
                                }
                              }else{
                                    Observable.just(dbPostList)
                                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result -> onRetrievePostListSuccess(result) },
                { onRetrievePostListError() }
            )

    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }
    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }
    private fun onRetrievePostListSuccess(postList: List<Post>){
        postListAdapter.updatePostList(postList)
    }
    private fun onRetrievePostListError(){
        errorMessage.value = R.string.post_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}