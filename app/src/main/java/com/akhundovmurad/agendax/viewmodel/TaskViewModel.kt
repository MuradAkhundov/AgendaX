package com.akhundovmurad.agendax.viewmodel

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
class TaskViewModel @Inject constructor(var tRepo : TaskRepository) : ViewModel() {

    val notesList = MutableLiveData<List<Task>>()


    init {
        loadAllTask()
    }

    private fun loadAllTask(){
        CoroutineScope(Dispatchers.Main).launch {
            notesList.value = tRepo.getAllTask()
        }
    }

}