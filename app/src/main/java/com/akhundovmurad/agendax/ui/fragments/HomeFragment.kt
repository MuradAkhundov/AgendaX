package com.akhundovmurad.agendax.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearSnapHelper
import com.akhundovmurad.agendax.R
import com.akhundovmurad.agendax.databinding.FragmentHomeBinding
import com.akhundovmurad.agendax.ui.adapter.TaskAdapter
import com.akhundovmurad.agendax.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var viewmodel : TaskViewModel
    private val lastDayInCalendar = Calendar.getInstance(Locale.ENGLISH)
    private val sdf = SimpleDateFormat("MMMM yyyy", Locale.ENGLISH)
    private val cal = Calendar.getInstance(Locale.ENGLISH)

    private val currentDate = Calendar.getInstance(Locale.ENGLISH)
    private val currentDay = currentDate[Calendar.DAY_OF_MONTH]
    private val currentMonth = currentDate[Calendar.MONTH]
    private val currentYear = currentDate[Calendar.YEAR]

    private var selectedDay: Int = currentDay
    private var selectedMonth: Int = currentMonth
    private var selectedYear: Int = currentYear
    private val dates = ArrayList<Date>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater,container,false)


        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerDays)

        lastDayInCalendar.add(Calendar.MONTH, 6)
        binding.fab.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.toModify)
        }



        viewmodel.notesList.observe(viewLifecycleOwner){
            if (it.isNotEmpty()){
                val adapter = TaskAdapter(requireContext(),it,viewmodel)
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

    override fun onResume() {
        super.onResume()
        viewmodel.loadAllTask()
    }
}