package com.elekiwi.expensestracker.core.di

import android.content.Context
import androidx.room.Room
import com.elekiwi.expensestracker.core.data.RoomExpenseDataSource
import com.elekiwi.expensestracker.core.data.local.CoreRepositoryImpl
import com.elekiwi.expensestracker.core.data.local.ExpenseDatabase
import com.elekiwi.expensestracker.core.domain.CoreRepository
import com.elekiwi.expensestracker.core.domain.LocalExpensesDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

var coreModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            ExpenseDatabase::class.java,
            "expense_database_db"
        ).build()
    }

    single {
        get<ExpenseDatabase>().dao
    }
    single {
        androidApplication().getSharedPreferences(
            "expenses_tracker_preferences", Context.MODE_PRIVATE
        )
    }

    singleOf(::RoomExpenseDataSource).bind<LocalExpensesDataSource>()
    singleOf(::CoreRepositoryImpl).bind<CoreRepository>()


}