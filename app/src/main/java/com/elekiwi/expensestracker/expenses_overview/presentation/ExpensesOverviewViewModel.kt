package com.elekiwi.expensestracker.expenses_overview.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elekiwi.expensestracker.core.domain.CoreRepository
import com.elekiwi.expensestracker.core.domain.Expense
import com.elekiwi.expensestracker.core.domain.LocalExpensesDataSource
import com.elekiwi.expensestracker.expenses_overview.presentation.util.randomColor
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class ExpensesOverviewViewModel(
    private val expensesDataSource: LocalExpensesDataSource,
    private val coreRepository: CoreRepository
): ViewModel() {

    var state by mutableStateOf(ExpensesOverviewState())
        private set

    fun onAction(action: ExpensesOverviewAction) {
        when (action) {
            ExpensesOverviewAction.LoadExpensesOverviewAndBalance -> {
                loadExpenseListAndBalance()
            }
            is ExpensesOverviewAction.OnDateChanged -> TODO()
            is ExpensesOverviewAction.OnDeleteExpense -> TODO()
        }
    }

    private fun loadExpenseListAndBalance() {
        viewModelScope.launch {
            val allDates = expensesDataSource.getAllDates()

            state = state.copy(
                expensesList = getExpensesListByDate(
                    allDates.lastOrNull() ?: ZonedDateTime.now()
                ),
                balance = coreRepository.getBalance() - expensesDataSource.getExpenseBalance(),
                pickedDate = allDates.lastOrNull() ?: ZonedDateTime.now(),
                datesList = allDates.reversed()

            )
        }
    }

    private suspend fun getExpensesListByDate(date: ZonedDateTime): List<Expense> {
        return expensesDataSource
            .getExpensesByDate(date)
            .reversed()
            .map { it.copy(color = randomColor()) }
    }
}