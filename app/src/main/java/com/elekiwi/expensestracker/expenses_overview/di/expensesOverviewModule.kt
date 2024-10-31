package com.elekiwi.expensestracker.expenses_overview.di

import com.elekiwi.expensestracker.expenses_overview.presentation.ExpensesOverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val expensesOverviewModule = module {
    viewModel { ExpensesOverviewViewModel(get(), get()) }
}