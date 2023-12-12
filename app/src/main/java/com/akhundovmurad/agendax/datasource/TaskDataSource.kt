package com.akhundovmurad.agendax.datasource

import com.akhundovmurad.agendax.di.TaskDao
import com.akhundovmurad.agendax.entity.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskDataSource(private val tdao : TaskDao) {

    suspend fun loadTasks() = withContext(Dispatchers.IO){
        tdao.loadTasks()
    }


    suspend fun addTask(task : Task) = withContext(Dispatchers.IO){
        tdao.addTask(task)
    }
}