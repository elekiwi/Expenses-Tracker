package com.elekiwi.expensestracker.balance.presentation

sealed interface BalanceAction {

    data class OnBalanceChanged(
        val newBalance: Double
    ): BalanceAction
    data object OnBalanceSaved: BalanceAction

}