package com.app.skstech.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.TimeZone

class DateUtils {

    companion object {

        @SuppressLint("SimpleDateFormat")
        fun getCurrentUniversaralDate(): String {
            val calendar: Calendar = Calendar.getInstance()
            // Format pattern: "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            dateFormat.timeZone =
                TimeZone.getTimeZone("UTC") // Ensure the UTC time zone for ISO 8601
            return dateFormat.format(calendar.time)

        }

    }

}