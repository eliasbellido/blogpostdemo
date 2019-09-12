package com.beyondthecode.blogpostsapp.ui.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.beyondthecode.blogpostsapp.R
import com.beyondthecode.blogpostsapp.databinding.ActivityPostListBinding
import com.beyondthecode.blogpostsapp.injection.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class PostListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostListBinding
    private lateinit var viewModel: PostListViewModel

    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_list)
        binding.recyclerviewPostlist.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)



        viewModel = ViewModelProvider(this, ViewModelFactory(this))
            .get(PostListViewModel::class.java)

        viewModel.errorMessage.observe(this, Observer {
            errorMessage -> if(errorMessage != null){
                                showError(errorMessage)
                            }else{
                                hideError()
                            }
        })


        binding.viewModel = viewModel
    }


    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)

        errorSnackbar?.setAction(R.string.retry, viewModel.errorClicklistener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }


}
