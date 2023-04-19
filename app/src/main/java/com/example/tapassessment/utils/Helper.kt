package com.example.tapassessment.utils

import java.text.SimpleDateFormat
import java.util.*


fun convertDateFormat(inputDate: String): String{
//    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
//    val inputDate = LocalDate.parse(inputDate, inputFormatter)

    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = inputFormat.parse(inputDate)
    val outputFormat = SimpleDateFormat("MMM yyyy", Locale.getDefault())
    return outputFormat.format(date)
}