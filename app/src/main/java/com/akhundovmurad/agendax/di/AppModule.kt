package com.akhundovmurad.agendax.di

import android.content.Context
import androidx.room.Room
import com.akhundovmurad.agendax.datasource.TaskDataSource
import com.akhundovmurad.agendax.db.RoomDb
import com.akhundovmurad.agendax.repo.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideTaskDataSource(tdao : TaskDao) : TaskDataSource {
        return TaskDataSource(tdao)
    }

    @Provides
    @Singleton
    fun provideTaskRepo(tds : TaskDataSource) : TaskRepository {
        return TaskRepository(tds)
    }

    @Provides
    @Singleton
    fun provideTaskDao(@ApplicationContext context : Context) : TaskDao{
        val db = Room.databaseBuilder(context, RoomDb::class.java ,"ToDo").build()
        return db.getTaskDao()
    }
}