package com.beyondthecode.blogpostsapp.network

import com.beyondthecode.blogpostsapp.model.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface PostApi {

    @GET("/posts")
    fun getPosts(): Observable<List<Post>>
}