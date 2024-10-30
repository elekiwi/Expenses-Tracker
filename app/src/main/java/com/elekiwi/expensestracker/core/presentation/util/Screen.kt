package com.elekiwi.expensestracker.core.presentation.util

sealed interface Screen {

    @kotlinx.serialization.Serializable
    data object ExpenseOverview: Screen

    @kotlinx.serialization.Serializable
    data class ExpenseDetails(val  expenseId: Int = -1): Screen

    @kotlinx.serialization.Serializable
    data object Balance: Screen
}