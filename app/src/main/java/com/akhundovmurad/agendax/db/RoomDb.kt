package com.akhundovmurad.agendax.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.akhundovmurad.agendax.di.TaskDao
import com.akhundovmurad.agendax.entity.Task

@Database(entities = [Task :: class], version = 1)
abstract class RoomDb : RoomDatabase() {

    abstract fun getTaskDao() : TaskDao
}