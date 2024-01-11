package com.akhundovmurad.agendax.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.akhundovmurad.agendax.databinding.FragmentModifyBinding
import com.akhundovmurad.agendax.entity.Task
import com.akhundovmurad.agendax.viewmodel.TaskViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModifyFragment(val task: Task) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentModifyBinding
    private val viewModel: TaskViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentModifyBinding.inflate(inflater, container, false)


        val date = task.date
        val priority = task.priority
        val taskCurrent = task.task



        binding.date.text = date
        binding.task.setText(taskCurrent)
        binding.add.setOnClickListener {
            val task = Task(task.id,priority,binding.task.text.toString(),task.date,task.isDone)
            viewModel.update(task)
            dismiss()
        }

        return binding.root
    }
}