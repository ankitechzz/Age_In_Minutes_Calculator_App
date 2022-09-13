package com.example.ageinminutescalculator

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private var tvAgeInMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)

        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)


btnDatePicker.setOnClickListener { clickDatePicker()}
    }

    private fun clickDatePicker(){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{ view,SelectedYear,SelectedMonth,SelectedDayOfMonth ->

            Toast.makeText(this, "Selected Year is $SelectedYear, Selected month is ${SelectedMonth+1}, Selected day is $SelectedDayOfMonth", Toast.LENGTH_SHORT).show()

            val selectedDate = "$SelectedDayOfMonth/${SelectedMonth+1}/$SelectedYear"
            tvSelectedDate?.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            val DateCalc = sdf.parse(selectedDate)

            DateCalc?.let{
                val selectedDateInMinutes = DateCalc.time/60000

                val CurrentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                      CurrentDate.let {
                val CurrentDateInMinutes = CurrentDate.time/60000

                 val DifferenceInMinutes = CurrentDateInMinutes - selectedDateInMinutes

                  tvAgeInMinutes?.text = DifferenceInMinutes.toString()
                   }

            }

        }
            ,year
            ,month
            ,day)
        dpd.datePicker.maxDate= System.currentTimeMillis()-86400000
        dpd.show()


    }
}