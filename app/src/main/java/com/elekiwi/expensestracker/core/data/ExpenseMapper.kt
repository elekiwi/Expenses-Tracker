package com.elekiwi.expensestracker.core.data

import com.elekiwi.expensestracker.core.data.local.ExpenseEntity
import com.elekiwi.expensestracker.core.domain.Expense
import java.time.Instant
import java.time.ZoneId

fun ExpenseEntity.toSpending(): Expense = Expense(
    expenseId = expenseId ?: -1,
    name = name,
    price = price,
    kilograms = kilograms,
    quantity = quantity,
    dateTimeUtc = Instant.parse(dateTimeUtc).atZone(
        ZoneId.of("UTC")
    )
)

fun Expense.toNewSpendingEntity(): ExpenseEntity = ExpenseEntity(
    expenseId = -1,
    name = name,
    price = price,
    kilograms = kilograms,
    quantity = quantity,
    dateTimeUtc = dateTimeUtc.toInstant().toString()
)

fun Expense.toEditedSpendingEntity(): ExpenseEntity = ExpenseEntity(
    expenseId = expenseId,
    name = name,
    price = price,
    kilograms = kilograms,
    quantity = quantity,
    dateTimeUtc = dateTimeUtc.toInstant().toString()
)
