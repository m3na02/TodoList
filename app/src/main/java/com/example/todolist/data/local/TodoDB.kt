package com.example.todolist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolist.domain.model.Todo

@Database(entities = [Todo::class], version =1, exportSchema = true)
abstract class TodoDB: RoomDatabase(){
    abstract fun todoDao(): TodoDao

}