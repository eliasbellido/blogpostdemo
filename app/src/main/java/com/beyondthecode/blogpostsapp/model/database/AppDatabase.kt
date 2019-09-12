package com.beyondthecode.blogpostsapp.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.beyondthecode.blogpostsapp.model.Post
import com.beyondthecode.blogpostsapp.model.PostDao

@Database(entities = [Post::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun postDao(): PostDao
}