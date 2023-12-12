package com.akhundovmurad.agendax.di

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.akhundovmurad.agendax.entity.Task

@Dao
interface TaskDao {

//    @Query("SELECT * FROM task WHERE date = :date ")
//    suspend fun loadTasks(date : String) : List<Task>


    @Query("SELECT * FROM task")
    suspend fun loadTasks() : List<Task>


    //if inserted before , then replace
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task)
}