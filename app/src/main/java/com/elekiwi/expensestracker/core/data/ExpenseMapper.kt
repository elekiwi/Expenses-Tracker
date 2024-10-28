package com.elekiwi.expensestracker.core.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.elekiwi.expensestracker.core.data.local.ExpenseEntity
import com.elekiwi.expensestracker.core.domain.Expense
import java.time.Instant
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
fun ExpenseEntity.toSpending(): Expense = Expense(
    expenseId = expenseId ?: 0,
    name = name,
    price = price,
    kilograms = kilograms,
    quantity = quantity,
    dateTimeUtc = Instant.parse(dateTimeUtc).atZone(ZoneId.of("UTC"))
)

@RequiresApi(Build.VERSION_CODES.O)
fun Expense.toNewSpendingEntity(): ExpenseEntity = ExpenseEntity(
    name = name,
    price = price,
    kilograms = kilograms,
    quantity = quantity,
    dateTimeUtc = dateTimeUtc.toInstant().toString()
)

@RequiresApi(Build.VERSION_CODES.O)
fun Expense.toEditedSpendingEntity(): ExpenseEntity = ExpenseEntity(
    expenseId = expenseId,
    name = name,
    price = price,
    kilograms = kilograms,
    quantity = quantity,
    dateTimeUtc = dateTimeUtc.toInstant().toString()
)
