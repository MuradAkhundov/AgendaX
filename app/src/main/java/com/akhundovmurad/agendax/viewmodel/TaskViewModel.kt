package com.akhundovmurad.agendax.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akhundovmurad.agendax.entity.Task
import com.akhundovmurad.agendax.repo.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private var tRepo: TaskRepository) : ViewModel() {

    val notesList = MutableLiveData<List<Task>>()


    init {
        loadAllTask()
    }

     fun loadAllTask() {
        CoroutineScope(Dispatchers.Main).launch {
            notesList.value = tRepo.getAllTask()
        }
    }

    fun delete(task: Task) = CoroutineScope(Dispatchers.Main).launch {
        tRepo.delete(task)
        loadAllTask()
    }

    fun tickTask(isDone: Boolean,taskId : Int) = CoroutineScope(Dispatchers.Main).launch{
        tRepo.tickTask(isDone, taskId)
        Log.e("Tag",taskId.toString())
    }

}