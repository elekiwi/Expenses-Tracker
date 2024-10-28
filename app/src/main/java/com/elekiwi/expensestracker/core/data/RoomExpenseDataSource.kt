package com.elekiwi.expensestracker.core.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.elekiwi.expensestracker.core.data.local.ExpenseDao
import com.elekiwi.expensestracker.core.domain.Expense
import com.elekiwi.expensestracker.core.domain.LocalExpensesDataSource
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

class RoomExpenseDataSource(
    private val dao: ExpenseDao
): LocalExpensesDataSource {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getAllExpenses(): List<Expense> {
        return dao.getAllExpenses().map { it.toSpending() }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getExpensesByDate(dateTimeUtc: ZonedDateTime): List<Expense> {
        return dao.getAllExpenses().map { it.toSpending() }
            .filter { expense ->
                expense.dateTimeUtc.dayOfMonth == dateTimeUtc.dayOfMonth
                        && expense.dateTimeUtc.month == dateTimeUtc.month
                        && expense.dateTimeUtc.year == dateTimeUtc.year
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getAllDates(): List<ZonedDateTime> {
        val uniqueDates = mutableSetOf<ZonedDateTime>()
        return dao.getAllDates().map { Instant.parse(it).atZone(ZoneId.of("UTC")) }
            .filter {
                uniqueDates.add(it)
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getExpenses(id: Int): Expense {
        return dao.getExpense(id).toSpending()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun upsertExpense(expense: Expense) {
        dao.upsertSpending(expense.toNewSpendingEntity())
    }

    override suspend fun getExpenseBalance(): Double {
        return dao.getExpenseBalance() ?: 0.0
    }

    override suspend fun deleteExpense(id: Int) {
        return dao.deleteExpense(id)
    }
}