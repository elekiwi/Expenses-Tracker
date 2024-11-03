package com.elekiwi.expensestracker.expenses_details.presentation

sealed interface ExpensesDetailsAction {
    data class UpdateName(val newName: String) : ExpensesDetailsAction
    data class UpdatePrice(val newPrice: Double) : ExpensesDetailsAction
    data class UpdateKilograms(val newKilograms: Double) : ExpensesDetailsAction
    data class UpdateQuantity(val newQuantity: Double) : ExpensesDetailsAction
    data object SaveExpense: ExpensesDetailsAction

}