package com.example.tapassessment.utils

import java.text.SimpleDateFormat
import java.util.*


fun convertDateFormat(inputDate: String): String{
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = inputFormat.parse(inputDate)
    val outputFormat = SimpleDateFormat("MMM yyyy", Locale.getDefault())
    return outputFormat.format(date)
}