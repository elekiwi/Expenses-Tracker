package com.elekiwi.expensestracker.expenses_overview.presentation

import com.elekiwi.expensestracker.core.domain.Expense
import java.time.ZonedDateTime

data class ExpensesOverviewState(
    val expensesList: List<Expense> = emptyList(),
    val dateList: List<ZonedDateTime> = emptyList(),
    val balance: Double = 0.0,
    val pickedDate: ZonedDateTime = ZonedDateTime.now(),
    val isDropdownMenuVisible: Boolean = false
)
