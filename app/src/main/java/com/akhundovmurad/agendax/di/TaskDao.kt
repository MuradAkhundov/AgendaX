package com.akhundovmurad.agendax.di

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.akhundovmurad.agendax.entity.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    suspend fun loadTasks() : List<Task>

    //if inserted before , then replace
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task)
    @Delete
    suspend fun delete(task : Task)

    @Query("UPDATE task SET isDone = :isDone WHERE id = :taskId ")
    fun tickTask(isDone: Boolean,taskId : Int)

}