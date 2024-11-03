package com.elekiwi.expensestracker.expenses_details.di

import com.elekiwi.expensestracker.expenses_details.domain.UpsertExpenseUseCase
import com.elekiwi.expensestracker.expenses_details.presentation.ExpensesDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val expensesDetailsModule = module {
    single { UpsertExpenseUseCase(get()) }
    viewModel { ExpensesDetailsViewModel(get(),get()) }
}