package com.akhundovmurad.agendax.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearSnapHelper
import com.akhundovmurad.agendax.R
import com.akhundovmurad.agendax.databinding.FragmentHomeBinding
import com.akhundovmurad.agendax.ui.adapter.TaskAdapter
import com.akhundovmurad.agendax.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewmodel: TaskViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)


        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerDays)

        binding.fab.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.toSave)
        }

        binding.search.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewmodel.search(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewmodel.search(it) }
                return true
            }
        })


        val adapter = TaskAdapter(requireContext(), emptyList(), viewmodel, this)
        binding.recyclerTask.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewmodel.notesList.collect {
                Log.e("tag", "observer")
                adapter.updateList(it)
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: TaskViewModel by activityViewModels()
        viewmodel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewmodel.loadAllTask()
        Log.e("Tag","resume")
    }
}


