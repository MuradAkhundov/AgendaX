package com.akhundovmurad.agendax.viewmodel

import androidx.lifecycle.ViewModel
import com.akhundovmurad.agendax.entity.Task
import com.akhundovmurad.agendax.repo.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModifyViewModel @Inject constructor(val tRepo : TaskRepository) : ViewModel(){

    fun addNote(task : Task) = CoroutineScope(Dispatchers.Main).launch { tRepo.addTask(task)  }
}