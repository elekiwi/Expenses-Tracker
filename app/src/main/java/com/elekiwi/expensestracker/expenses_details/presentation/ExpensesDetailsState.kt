package com.elekiwi.expensestracker.expenses_details.presentation

import com.elekiwi.expensestracker.core.domain.Expense

data class ExpensesDetailsState(
    val expenseId: Int = -1,
    val name: String = "",
    val price: Double = 0.0,
    val kilograms: Double = 0.0,
    val quantity: Double = 0.0,

)
