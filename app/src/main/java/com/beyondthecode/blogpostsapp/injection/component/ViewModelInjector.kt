package com.beyondthecode.blogpostsapp.injection.component

import com.beyondthecode.blogpostsapp.injection.module.NetworkModule
import com.beyondthecode.blogpostsapp.ui.post.PostListViewModel
import com.beyondthecode.blogpostsapp.ui.post.PostViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters
 * */
@Singleton
@Component(modules = [NetworkModule::class])
interface ViewModelInjector {

    /**
     * Injects required dependencies into the specified PostListViewModel
     * @param postListViewModel PostListViewModel in which to inject the dependencies
     * */
    fun inject(postListViewModel: PostListViewModel)

    /**
     * Injects required dependencies into the specified Postviewmodel.
     * @param postViewModel PostViewModel in which to inject the dependencies
     * */
    fun inject(postViewModel: PostViewModel)

    @Component.Builder
    interface Builder{
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}