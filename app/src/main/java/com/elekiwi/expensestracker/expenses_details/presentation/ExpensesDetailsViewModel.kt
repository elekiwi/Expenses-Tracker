package com.elekiwi.expensestracker.expenses_details.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elekiwi.expensestracker.core.domain.Expense
import com.elekiwi.expensestracker.core.domain.LocalExpensesDataSource
import com.elekiwi.expensestracker.expenses_details.domain.UpsertExpenseUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class ExpensesDetailsViewModel(
    private val upsertExpenseUseCase: UpsertExpenseUseCase,
    private val expensesDataSource: LocalExpensesDataSource
    ) : ViewModel(){

    var state by mutableStateOf(ExpensesDetailsState())
        private set

    private val _eventChannel = Channel<ExpensesDetailsEvent>()
    val event = _eventChannel.receiveAsFlow()

    fun onAction(action: ExpensesDetailsAction) {
        when (action) {
            is ExpensesDetailsAction.UpdateKilograms -> {
                state = state.copy(kilograms = action.newKilograms)
            }
            is ExpensesDetailsAction.UpdateName -> {
                state = state.copy(name = action.newName)
            }
            is ExpensesDetailsAction.UpdatePrice -> {
                state = state.copy(price = action.newPrice)
            }
            is ExpensesDetailsAction.UpdateQuantity -> {
                state = state.copy(quantity = action.newQuantity)
            }

            ExpensesDetailsAction.SaveExpense -> {
                viewModelScope.launch {
                    if (saveExpense()) {
                        _eventChannel.send(ExpensesDetailsEvent.SaveSuccess)
                    } else{
                        _eventChannel.send(ExpensesDetailsEvent.SaveFailed)
                    }
                }
            }

            is ExpensesDetailsAction.LoadExpense -> {
                loadExpense(action.id)
            }
        }
    }

    private fun loadExpense(id: Int) {

        if (id == -1) {
            return
        }

        viewModelScope.launch {
            val expense = expensesDataSource.getExpenses(id)

            state = state.copy(
                expenseId = id,
                name = expense.name,
                price = expense.price,
                kilograms = expense.kilograms,
                quantity = expense.quantity
            )

        }
    }

    private suspend fun saveExpense(): Boolean {
        val expense = Expense(
            expenseId = state.expenseId,
            name = state.name,
            price = state.price,
            kilograms = state.kilograms,
            quantity = state.quantity,
            dateTimeUtc = ZonedDateTime.now()
        )

        return upsertExpenseUseCase(expense)
    }
 }