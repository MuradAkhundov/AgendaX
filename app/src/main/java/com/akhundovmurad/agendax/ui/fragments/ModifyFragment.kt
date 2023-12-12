package com.akhundovmurad.agendax.ui.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.akhundovmurad.agendax.R
import com.akhundovmurad.agendax.databinding.FragmentModifyBinding
import com.akhundovmurad.agendax.entity.Task
import com.akhundovmurad.agendax.viewmodel.ModifyViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar

@AndroidEntryPoint
class ModifyFragment : Fragment(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private lateinit var binding: FragmentModifyBinding
    private lateinit var viewModel : ModifyViewModel
    private var priority = "Low"
    private var day = 0
    private var month: Int = 0
    private var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0
    var myDay = 0
    var myMonth: Int = 0
    var myYear: Int = 0
    var myHour: Int = 0
    var myMinute: Int = 0
    var selectedDate = ""
    var selectedTime = ""
    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentModifyBinding.inflate(inflater, container, false)
        constructSpinner()
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val current = formatter.format(time)
        selectedDate = current.toString()
        binding.date.text = selectedDate
//        Log.e("tag", current.toString())

        binding.modifyDate.setOnClickListener {
            onModifyDateClicked()
        }
        binding.add.setOnClickListener {
            insertToDb()
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempviewmodel : ModifyViewModel by viewModels()
        viewModel = tempviewmodel
    }

    private fun insertToDb() {
        val toDo = binding.task.text.toString()
        if (toDo.isEmpty()){
            Toast.makeText(requireContext(),"Please Fill Task Area.", Toast.LENGTH_SHORT).show()
        }
        else{
            val task = Task(null,priority,toDo, binding.date.text.toString()  ,false)
            Log.e("tag date",task.date)
            task.priority?.let { Log.e("tag prior", it) }

            viewModel.addNote(task)
        }
    }


    private fun onModifyDateClicked() {

        val calendar : Calendar = Calendar.getInstance()
        day = calendar.get(Calendar.DAY_OF_MONTH)
        month = calendar.get(Calendar.MONTH)
        year = calendar.get(Calendar.YEAR)
        val datePickerDialog =
            DatePickerDialog(requireContext(),this,year,month,day)
        datePickerDialog.show()
    }



    private fun constructSpinner() {
        val list = listOf("Low", "Medium", "High")
        val spinAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.prioritySpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        binding.priorityColor.backgroundTintList =
                            ContextCompat.getColorStateList(requireContext(), R.color.green)
                        priority = list[0]
                    }

                    1 -> {
                        binding.priorityColor.backgroundTintList =
                            ContextCompat.getColorStateList(requireContext(), R.color.yellow)
                        priority = list[1]
                    }

                    2 -> {
                        binding.priorityColor.backgroundTintList =
                            ContextCompat.getColorStateList(requireContext(), R.color.red)
                        priority = list[2]
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }


        }

        with(binding.prioritySpinner) {
            adapter = spinAdapter
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        myDay = dayOfMonth
        myYear = year
        myMonth = month
        val calendar : Calendar = Calendar.getInstance()
        calendar.set(myYear,myMonth,myDay)
        selectedDate = SimpleDateFormat("dd-MM-yyyy").format(calendar.time)
        hour = calendar.get(Calendar.HOUR)
        minute = calendar.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(requireContext(),this,hour, minute, DateFormat.is24HourFormat(requireContext()))
        timePickerDialog.show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        myHour = hourOfDay
        myMinute = minute

        val calendar : Calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR,myHour)
        calendar.set(Calendar.MINUTE,myMinute)
        selectedTime = SimpleDateFormat("HH:mm").format(calendar.time)
        binding.date.text = "$selectedDate $selectedTime"
    }


}