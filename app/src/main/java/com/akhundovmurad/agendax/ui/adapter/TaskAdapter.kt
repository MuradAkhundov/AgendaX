package com.akhundovmurad.agendax.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akhundovmurad.agendax.databinding.TaskItemBinding
import com.akhundovmurad.agendax.entity.Task

class TaskAdapter(val mContext : Context, private val list : List<Task>) : RecyclerView.Adapter<TaskAdapter.TaskDesignHolder>(){


    inner class TaskDesignHolder(private val binding : TaskItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskDesignHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TaskItemBinding.inflate(inflater,parent,false)
        return TaskDesignHolder(binding)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: TaskDesignHolder, position: Int) {
    }
}