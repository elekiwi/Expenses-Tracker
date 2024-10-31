package com.elekiwi.expensestracker.expenses_overview.presentation

sealed interface ExpensesOverviewAction {
    data object LoadExpensesOverviewAndBalance: ExpensesOverviewAction
    data class OnDateChanged(val newDate: Int): ExpensesOverviewAction
    data class OnDeleteExpense(val expenseId: Int): ExpensesOverviewAction
}