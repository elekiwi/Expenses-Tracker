package com.elekiwi.expensestracker.core.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ExpenseDao {

    @Upsert
    suspend fun upsertSpending(entity: ExpenseEntity)

    @Query("SELECT * FROM expenseentity")
    suspend fun getAllExpenses(): List<ExpenseEntity>

    @Query("SELECT * FROM expenseentity WHERE expenseId = :id")
    suspend fun getExpense(id: Int): ExpenseEntity

    @Query("SELECT dateTimeUtc FROM expenseentity")
    suspend fun getAllDates(): List<String>

    @Query("SELECT SUM(price) FROM expenseentity")
    suspend fun getExpenseBalance(): Double?

    @Query("DELETE FROM expenseentity WHERE expenseId = :id")
    suspend fun deleteExpense(id: Int)
}