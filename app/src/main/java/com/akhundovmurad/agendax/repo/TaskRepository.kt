package com.akhundovmurad.agendax.repo

import com.akhundovmurad.agendax.datasource.TaskDataSource
import com.akhundovmurad.agendax.entity.Task

class TaskRepository(private val tds : TaskDataSource) {


    suspend fun getAllTask() : List<Task> = tds.loadTasks()

    suspend fun addTask(task : Task) = tds.addTask(task)
}