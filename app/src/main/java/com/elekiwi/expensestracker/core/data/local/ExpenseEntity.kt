package com.elekiwi.expensestracker.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val expenseId: Int? = null,

    val name: String,
    val price: Double,
    val kilograms: Double,
    val quantity: Double,
    val dateTimeUtc: String
)
