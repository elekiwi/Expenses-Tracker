package com.elekiwi.expensestracker.core.domain

import java.time.ZonedDateTime

data class Expense(
    val expenseId: Int?,
    val name: String,
    val price: Double,
    val kilograms: Double,
    val quantity: Double,
    val dateTimeUtc: ZonedDateTime,
    val color : Int = 0
)
