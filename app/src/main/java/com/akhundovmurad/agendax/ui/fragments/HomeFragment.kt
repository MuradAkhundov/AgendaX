package com.akhundovmurad.agendax.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.akhundovmurad.agendax.R
import com.akhundovmurad.agendax.databinding.FragmentHomeBinding
import com.akhundovmurad.agendax.ui.adapter.TaskAdapter
import com.akhundovmurad.agendax.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var viewmodel : TaskViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater,container,false)


        binding.fab.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.toModify)
        }

        viewmodel.notesList.observe(viewLifecycleOwner){
            if (it.isNotEmpty()){
                val adapter = TaskAdapter(requireContext(),it)
                binding.recyclerTask.adapter = adapter
            }
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : TaskViewModel by viewModels()
        viewmodel = tempViewModel
    }

}