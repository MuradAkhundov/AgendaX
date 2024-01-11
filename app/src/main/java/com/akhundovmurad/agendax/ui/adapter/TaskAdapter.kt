package com.akhundovmurad.agendax.ui.adapter

import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.akhundovmurad.agendax.R
import com.akhundovmurad.agendax.databinding.TaskItemBinding
import com.akhundovmurad.agendax.entity.Task
import com.akhundovmurad.agendax.ui.fragments.HomeFragmentDirections
import com.akhundovmurad.agendax.ui.fragments.ModifyFragment
import com.akhundovmurad.agendax.viewmodel.TaskViewModel

class TaskAdapter(private val mContext : Context, private var list : List<Task>, private val viewModel: TaskViewModel, private val fragment : Fragment) : RecyclerView.Adapter<TaskAdapter.TaskDesignHolder>(){


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

        b.task.text = itemlist.task
        b.time.text = itemlist.date
        if (itemlist.isDone){
            b.task.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            b.time.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            b.checkbox.isChecked = true
        }
        b.root.setOnClickListener{
            if (!itemlist.isDone) {
                val bottomSheetFragment = ModifyFragment(itemlist)
                bottomSheetFragment.show(fragment.childFragmentManager, bottomSheetFragment.tag)
            }
            else{
                Toast.makeText(mContext,"You can't modify finished tasks.",Toast.LENGTH_SHORT).show()
            }
        }

        b.priority.backgroundTintList = when(itemlist.priority){
            "Low" -> ContextCompat.getColorStateList(mContext, R.color.green)
            "Medium" -> ContextCompat.getColorStateList(mContext, R.color.yellow)
            "High" -> ContextCompat.getColorStateList(mContext, R.color.red)
            else -> ContextCompat.getColorStateList(mContext, R.color.green)

        }

        b.delete.setOnClickListener {
            viewModel.delete(itemlist)
        }

        b.checkbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                b.task.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                b.time.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                val task = Task(itemlist.id,itemlist.priority,itemlist.task,itemlist.date,true)
                viewModel.update(task)
            }
            else{
                b.task.paintFlags = 0
                b.time.paintFlags = 0
                val task = Task(itemlist.id,itemlist.priority,itemlist.task,itemlist.date,false)
                viewModel.update(task)
            }
        }



    }
    fun updateList(list : List<Task>){
        this.list = list
        notifyDataSetChanged()
    }
}