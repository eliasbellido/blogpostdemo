package com.beyondthecode.blogpostsapp.base

import androidx.lifecycle.ViewModel
import com.beyondthecode.blogpostsapp.injection.component.DaggerViewModelInjector
import com.beyondthecode.blogpostsapp.injection.component.ViewModelInjector
import com.beyondthecode.blogpostsapp.injection.module.NetworkModule
import com.beyondthecode.blogpostsapp.ui.post.PostListViewModel
import com.beyondthecode.blogpostsapp.ui.post.PostViewModel

abstract class BaseViewModel: ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     * */
    private fun inject(){
        when(this){
            is PostListViewModel -> injector.inject(this)
            is PostViewModel -> injector.inject(this)

        }
    }
}