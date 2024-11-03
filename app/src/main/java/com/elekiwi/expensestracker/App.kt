package com.elekiwi.expensestracker

import android.app.Application
import com.elekiwi.expensestracker.balance.di.balanceModule
import com.elekiwi.expensestracker.core.di.coreModule
import com.elekiwi.expensestracker.expenses_details.di.expensesDetailsModule
import com.elekiwi.expensestracker.expenses_overview.di.expensesOverviewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                coreModule,
                balanceModule,
                expensesOverviewModule,
                expensesDetailsModule
            )
        }
    }
}
