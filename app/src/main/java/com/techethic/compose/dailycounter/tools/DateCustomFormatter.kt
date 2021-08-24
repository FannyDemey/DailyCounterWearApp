package com.techethic.compose.dailycounter.tools

import android.util.Log
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object DateCustomFormatter {
    private const val DATE_IN_DB_PATTERN = "yyyyMMdd"

    fun formatDateForQuery(date : Date, locale : Locale) : String{
        val simpleDateFormater = SimpleDateFormat(DATE_IN_DB_PATTERN, locale)
        return simpleDateFormater.format(date)
    }

    fun formatDateFromDbToDisplayableDate(dateInDb : String, locale : Locale) : String {
        Log.d("Fanny","dateInDb $dateInDb")
        val inputFormat = SimpleDateFormat(DATE_IN_DB_PATTERN, locale)

        val parsedDate = inputFormat.parse(dateInDb)
        Log.d("Fanny","parsedDate $parsedDate")

        val simpleDateFormater = SimpleDateFormat("d MMM", locale)
        return simpleDateFormater.format(parsedDate)
    }

    fun formatTimestampToTime(timestamp: Long, locale : Locale) : String {
        val stamp = Timestamp(timestamp)
        val date = Date(stamp.time)
        val simpleDateFormater = SimpleDateFormat("HH:mm", locale)
        return simpleDateFormater.format(date)
    }
}