package com.elekiwi.expensestracker.expenses_details.domain

import com.elekiwi.expensestracker.core.domain.Expense
import com.elekiwi.expensestracker.core.domain.LocalExpensesDataSource

class UpsertExpenseUseCase(
    private val expenseDataSource: LocalExpensesDataSource
) {
    suspend operator fun invoke(expense: Expense): Boolean {
        if (expense.name.isBlank()){
            return false
        }
        if (expense.price < 0){
            return false
        }
        if (expense.kilograms < 0){
            return false
        }
        if (expense.quantity < 0){
            return false
        }

        expenseDataSource.upsertExpense(expense)

        return true
    }

}