package com.elekiwi.expensestracker.expenses_overview.presentation.util

import java.time.ZonedDateTime

fun ZonedDateTime.formatDate(): String {
    return "${dayOfMonth}-${monthValue}-${year}"
}