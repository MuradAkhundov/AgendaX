package com.akhundovmurad.agendax.repo

import com.akhundovmurad.agendax.datasource.TaskDataSource
import com.akhundovmurad.agendax.entity.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val tds : TaskDataSource) {


    fun getAllTask(): Flow<List<Task>> = tds.loadTasks()

    suspend fun addTask(task : Task) = tds.addTask(task)

    suspend fun delete(task: Task) = tds.delete(task)

    suspend fun update(task: Task) = tds.update(task)

    suspend fun search(searchText : String) : List<Task>  = tds.search(searchText)
}