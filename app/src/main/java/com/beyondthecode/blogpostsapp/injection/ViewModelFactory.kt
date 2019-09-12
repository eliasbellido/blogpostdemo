package com.beyondthecode.blogpostsapp.injection

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.beyondthecode.blogpostsapp.model.database.AppDatabase
import com.beyondthecode.blogpostsapp.ui.post.PostListViewModel
import com.beyondthecode.blogpostsapp.utils.ROOM_DBNAME
import java.lang.IllegalArgumentException

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PostListViewModel::class.java)){
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, ROOM_DBNAME).build()
            @Suppress("UNCHECKED_CAST")
            return PostListViewModel(db.postDao()) as T
        }
        throw IllegalArgumentException("clase ViewModel desconocida")
    }

}