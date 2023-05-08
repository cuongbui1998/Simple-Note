package com.example.simplenote.utils

import android.content.Context
import com.example.simplenote.AndroidApplication
import java.text.SimpleDateFormat
import java.util.*

fun String.addEmptyLines(lines: Int) = this + "\n".repeat(lines)

fun appContext(): Context = AndroidApplication.instance.applicationContext

fun formatLastModifiedDate(date: Date): String {
    val pattern = "MMMM dd, yyyy"
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return dateFormat.format(date)
}