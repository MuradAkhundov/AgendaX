package com.akhundovmurad.agendax.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true) val id : Int?,
    @ColumnInfo(name = "priority") val priority : String?,
    @ColumnInfo(name = "task") val task : String?,
    @ColumnInfo(name = "date") val date : String,
    @ColumnInfo(name = "isDone") val isDone : Boolean,
) : Serializable