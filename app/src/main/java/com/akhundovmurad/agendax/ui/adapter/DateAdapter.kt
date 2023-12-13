package com.akhundovmurad.agendax.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.akhundovmurad.agendax.R
import com.akhundovmurad.agendax.databinding.CalendarItemBinding
import com.akhundovmurad.agendax.databinding.TaskItemBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class DateAdapter(private val context: Context,
                  private val data: ArrayList<Date>,
                  private val currentDate: Calendar,
                  private val changeMonth: Calendar?): RecyclerView.Adapter<DateAdapter.ViewHolder>() {
    private var mListener: AdapterView.OnItemClickListener? = null
    private var index = -1
    private var selectCurrentDate = true
    private val currentMonth = currentDate[Calendar.MONTH]
    private val currentYear = currentDate[Calendar.YEAR]
    private val currentDay = currentDate[Calendar.DAY_OF_MONTH]

    private val selectedDay =
        when {
            changeMonth != null -> changeMonth.getActualMinimum(Calendar.DAY_OF_MONTH)
            else -> currentDay
        }
    private val selectedMonth =
        when {
            changeMonth != null -> changeMonth[Calendar.MONTH]
            else -> currentMonth
        }
    private val selectedYear =
        when {
            changeMonth != null -> changeMonth[Calendar.YEAR]
            else -> currentYear
        }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CalendarItemBinding.inflate(inflater,parent,false)
        return ViewHolder(binding,mListener!!)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sdf = SimpleDateFormat("EEE MMM dd HH:mm:ss", Locale.ENGLISH)
        val cal = Calendar.getInstance()
        cal.time = data[position]

        val b = holder.binding
        val list = data[position]


        val displayMonth = cal[Calendar.MONTH]
        val displayYear= cal[Calendar.YEAR]
        val displayDay = cal[Calendar.DAY_OF_MONTH]

        try {
            val dayInWeek = sdf.parse(cal.time.toString())!!
            sdf.applyPattern("EEE")
            b.textDate.text = sdf.format(dayInWeek).toString()
        } catch (ex: ParseException) {
            Log.v("Exception", ex.localizedMessage!!)
        }
        b.textDay.text = cal[Calendar.DAY_OF_MONTH].toString()
        // we will add onClickListener here
    }

    inner class ViewHolder(val binding : CalendarItemBinding, val listener: AdapterView.OnItemClickListener): RecyclerView.ViewHolder(binding.root) {
    }

    interface OnItemClickListener : AdapterView.OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

}
