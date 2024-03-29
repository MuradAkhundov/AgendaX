package com.akhundovmurad.agendax.di

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.akhundovmurad.agendax.entity.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun loadTasks(): Flow<List<Task>>

    //if inserted before , then replace
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task)
    @Delete
    suspend fun delete(task : Task)

    @Update
    suspend fun update(task : Task)

    @Query("SELECT * FROM task WHERE task LIKE '%' || :searchText || '%'")
    suspend fun search(searchText : String) : List<Task>

}