package com.elekiwi.expensestracker.balance.di

import com.elekiwi.expensestracker.balance.presentation.BalanceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val balanceModule = module {
    viewModel { BalanceViewModel(get()) }
}