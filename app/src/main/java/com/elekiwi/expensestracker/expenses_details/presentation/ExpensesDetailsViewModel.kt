package com.elekiwi.expensestracker.expenses_details.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elekiwi.expensestracker.core.domain.Expense
import com.elekiwi.expensestracker.expenses_details.domain.UpsertExpenseUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class ExpensesDetailsViewModel(
    private val upsertExpenseUseCase: UpsertExpenseUseCase
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
        }
    }

    private suspend fun saveExpense(): Boolean {
        val expense = Expense(
            expenseId = null,
            name = state.name,
            price = state.price,
            kilograms = state.kilograms,
            quantity = state.quantity,
            dateTimeUtc = ZonedDateTime.now()
        )

        return upsertExpenseUseCase(expense)
    }
 }