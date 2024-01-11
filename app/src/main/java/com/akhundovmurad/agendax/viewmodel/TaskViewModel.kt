package com.akhundovmurad.agendax.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akhundovmurad.agendax.entity.Task
import com.akhundovmurad.agendax.repo.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private var tRepo: TaskRepository) : ViewModel() {

    private val _notesList = MutableStateFlow<List<Task>>(emptyList())
    val notesList: StateFlow<List<Task>> get() = _notesList

    init {
        loadAllTask()
    }

    fun loadAllTask() {
        viewModelScope.launch {
            tRepo.getAllTask().collect {
                _notesList.value = it
            }
        }
    }
    fun delete(task: Task) = viewModelScope.launch {
        tRepo.delete(task)
        loadAllTask()
    }

    fun update(task: Task) = viewModelScope.launch {
        tRepo.update(task)
    }

    fun search(searchText: String) = viewModelScope.launch {
        _notesList.value = tRepo.search(searchText)
    }
}