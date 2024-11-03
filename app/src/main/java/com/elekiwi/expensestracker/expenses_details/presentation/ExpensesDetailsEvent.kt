package com.elekiwi.expensestracker.expenses_details.presentation

sealed interface ExpensesDetailsEvent {

    data object SaveFailed : ExpensesDetailsEvent
    data object SaveSuccess : ExpensesDetailsEvent
}