package com.beyondthecode.blogpostsapp.ui.post

import androidx.lifecycle.MutableLiveData
import com.beyondthecode.blogpostsapp.base.BaseViewModel
import com.beyondthecode.blogpostsapp.model.Post

class PostViewModel:BaseViewModel(){

    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<String>()

    fun bind(post: Post){
        postTitle.value = post.title
        postBody.value = post.body
    }

    fun getPostTitle(): MutableLiveData<String>{
        return postTitle
    }

    fun getPostBody(): MutableLiveData<String>{
        return postBody
    }
}