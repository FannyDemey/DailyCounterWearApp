package com.techethic.compose.dailycounter.tools

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object DateCustomFormatter {
    private const val DATE_IN_DB_PATTERN = "yyyyMMdd"

    fun formatDateForQuery(date : Date) : String{
        val simpleDateFormater = SimpleDateFormat(DATE_IN_DB_PATTERN, Locale.FRANCE)
        return simpleDateFormater.format(date)
    }

    fun formatDateFromDbToDisplayableDate(dateInDb : String) : String {
        Log.d("Fanny","dateInDb $dateInDb")
        val inputFormat = SimpleDateFormat(DATE_IN_DB_PATTERN, Locale.FRANCE)

        val parsedDate = inputFormat.parse(dateInDb)
        Log.d("Fanny","parsedDate $parsedDate")

        val simpleDateFormater = SimpleDateFormat("d MMM", Locale.FRANCE)
        return simpleDateFormater.format(parsedDate)
    }
}