package com.akhundovmurad.agendax.ui.adapter

import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.akhundovmurad.agendax.databinding.TaskItemBinding
import com.akhundovmurad.agendax.entity.Task
import com.akhundovmurad.agendax.viewmodel.TaskViewModel

class TaskAdapter(val mContext : Context, private val list : List<Task>, private val viewModel: TaskViewModel) : RecyclerView.Adapter<TaskAdapter.TaskDesignHolder>(){


    inner class TaskDesignHolder( val binding : TaskItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskDesignHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TaskItemBinding.inflate(inflater,parent,false)
        return TaskDesignHolder(binding)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: TaskDesignHolder, position: Int) {

        val b = holder.binding
        val itemlist = list[position]


        Log.e("tag", itemlist.isDone.toString())

        b.task.text = itemlist.task
        if (itemlist.isDone){
            b.task.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }

        b.delete.setOnClickListener {
            viewModel.delete(itemlist)
            Toast.makeText(mContext,"Deleted",Toast.LENGTH_SHORT).show()
        }

        b.checkbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                b.task.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                Log.e("tag",position.toString())
                viewModel.tickTask(true,position)
            }
        }

    }
}