package com.example.todolist.di

import android.content.Context
import androidx.room.Room
import com.example.todolist.data.local.TodoDB
import com.example.todolist.data.local.TodoDao
import com.example.todolist.data.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoModule {

    @Provides
    @Singleton
    fun provideLocalDb(@ApplicationContext context: Context): TodoDB{
        return Room.databaseBuilder(
            context,
            TodoDB::class.java,
            "local_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(db: TodoDB): TodoDao = db.todoDao()

    @Provides
    @Singleton
    fun provideTodoRepository(dao: TodoDao): TodoRepository = TodoRepository(dao = dao)

}