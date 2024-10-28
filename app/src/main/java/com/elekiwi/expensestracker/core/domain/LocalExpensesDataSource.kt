package com.elekiwi.expensestracker.core.domain

import com.elekiwi.expensestracker.core.data.local.ExpenseEntity
import java.time.ZonedDateTime

interface LocalExpensesDataSource {

    suspend fun getAllExpenses(): List<Expense>

    suspend fun getExpensesByDate(
        dateTimeUtc: ZonedDateTime
    ): List<Expense>

    suspend fun getAllDates(): List<ZonedDateTime>

    suspend fun getExpenses(id: Int): Expense

    suspend fun upsertExpense(expense: Expense)

    suspend fun getExpenseBalance(): Double

    suspend fun deleteExpense(id: Int)


}