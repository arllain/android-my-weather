package br.com.arllain.myweather.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DateTimeUtils {

    companion object {

        fun getRelativeDateFormat(date: Long): String {
            return getDateFormat(date)
        }

        private fun getDateFormat(instantLong: Long, isDefault: Boolean = true): String {
            val instant = Instant.ofEpochSecond(instantLong)
            val date = instant.toString()
//            val locale = Locale.ENGLISH
            val locale = Locale("pt", "BR")
            var inputDate: LocalDateTime? = null
            var outputDate: String? = null

            val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", locale)
            val outputFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm a", locale)

            inputDate = LocalDateTime.parse(date, inputFormatter)
            outputDate = outputFormatter.format(inputDate)

            return outputDate
        }
    }
}
