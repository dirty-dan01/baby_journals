package com.example.baby_journals.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.Period

@RequiresApi(Build.VERSION_CODES.O)
fun formatAge(birthDate: LocalDate): String {
    val period = Period.between(birthDate, LocalDate.now())
    return when {
        period.years > 0 && period.months > 0 -> "${period.years} years ${period.months} months"
        period.years > 0 -> "${period.years} years"
        period.months > 0 && period.days > 0 -> "${period.months} months ${period.days} days"
        period.months > 0 -> "${period.months}"
        else -> "${period.days}"
    }
}