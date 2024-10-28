package com.elekiwi.expensestracker.core.data.local

import android.content.SharedPreferences
import com.elekiwi.expensestracker.core.domain.CoreRepository

class CoreRepositoryImpl(
    private val prefs: SharedPreferences
): CoreRepository {
    override suspend fun updateBalance(balance: Double) {
        prefs.edit().putFloat(KEY_BALANCE, balance.toFloat()).apply()
    }

    override suspend fun getBalance(): Double{
       return prefs.getFloat(KEY_BALANCE, 0f).toDouble()
    }

    companion object {
        private const val KEY_BALANCE = "KEY_BALANCE"
    }
}