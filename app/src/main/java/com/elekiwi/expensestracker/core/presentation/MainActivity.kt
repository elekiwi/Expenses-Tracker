package com.elekiwi.expensestracker.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elekiwi.expensestracker.balance.presentation.BalanceScreenCore
import com.elekiwi.expensestracker.core.presentation.ui.theme.ExpensesTrackerTheme
import com.elekiwi.expensestracker.core.presentation.util.Screen
import com.elekiwi.expensestracker.expenses_overview.presentation.ExpensesOverviewScreenCore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpensesTrackerTheme {
                Navigation(modifier = Modifier.fillMaxSize())
            }
        }
    }

    @Composable
    fun Navigation(modifier: Modifier = Modifier) {
        val navController = rememberNavController()

        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = Screen.ExpenseOverview
        ) {
            composable<Screen.ExpenseOverview> {
                ExpensesOverviewScreenCore(
                    onBalanceClick = {
                        navController.navigate(Screen.Balance)
                    },
                    onEditClick = {
                        //TODO: How to pass the object id to this screen???
                        navController.navigate(Screen.ExpenseDetails(expenseId = -1))
                    },
                    onAddExpenseClick = {
                        navController.navigate(Screen.ExpenseDetails)
                    }
                )
            }

            composable<Screen.ExpenseDetails> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Expenses Details")
                }
            }


            composable<Screen.Balance> {
                BalanceScreenCore(
                    onSaveClick = {
                        navController.popBackStack()
                    })
            }
        }
    }
}
