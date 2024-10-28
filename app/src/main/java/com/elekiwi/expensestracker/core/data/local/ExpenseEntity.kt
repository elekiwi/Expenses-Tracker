package com.elekiwi.expensestracker.core.data.local

import androidx.room.Entity

@Entity
data class ExpenseEntity(
    val expenseId: Int? = null,
    val name: String,
    val price: Double,
    val kilograms: Double,
    val quantity: Double,
    val dateTimeUtc: String
)
