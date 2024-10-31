package com.elekiwi.expensestracker.expenses_overview.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elekiwi.expensestracker.core.presentation.ui.theme.ExpensesTrackerTheme
import com.elekiwi.expensestracker.core.presentation.ui.theme.montserrat
import com.elekiwi.expensestracker.core.presentation.util.Background
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExpensesOverviewScreenCore(
    viewmodel: ExpensesOverviewViewModel = koinViewModel(),
    onBalanceClick: () -> Unit,
    onEditClick: () -> Unit,
    onAddExpenseClick: () -> Unit
) {

    LaunchedEffect(true) {
        viewmodel.onAction(ExpensesOverviewAction.LoadExpensesOverviewAndBalance)
    }

    ExpensesOverviewScreen(
        state = viewmodel.state,
        onAction = viewmodel::onAction,
        onBalanceClick = onBalanceClick,
        onEditClick = onEditClick,
        onAddExpenseClick = onAddExpenseClick,
        onDeleteExpense = {
            viewmodel.onAction(ExpensesOverviewAction.OnDeleteExpense(it))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesOverviewScreen(
    state: ExpensesOverviewState,
    onAction: (ExpensesOverviewAction) -> Unit,
    onBalanceClick: () -> Unit,
    onEditClick: () -> Unit,
    onAddExpenseClick: () -> Unit,
    onDeleteExpense: (Int) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )

    Scaffold(
        modifier = Modifier.nestedScroll(
            scrollBehavior.nestedScrollConnection
        ),
        floatingActionButton = {
            Column {

                FloatingActionButton(
                    onClick = { onAddExpenseClick() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Expense"
                    )
                }
                Spacer(Modifier.height(40.dp))
            }
        },
        topBar = {
            ExpensesOverviewTopBar(
                modifier = Modifier,
                scrollBehavior = scrollBehavior,
                balance = state.balance,
                onBalanceClick = onBalanceClick
            )

        }
    ) { padding ->
        Background()
        Column(
            modifier = Modifier.padding(padding)
        ) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesOverviewTopBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    balance: Double,
    onBalanceClick: () -> Unit
) {

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent
        ),
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                "$${balance}",
                fontSize = 35.sp,
                maxLines = 1,
                fontFamily = montserrat,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.primary
            )
        },
        actions = {
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clip(RoundedCornerShape(13.dp))
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary.copy(0.6f),
                        shape = RoundedCornerShape(13.dp)
                    )
                    .background(MaterialTheme.colorScheme.primaryContainer.copy(0.3f))
                    .clickable { onBalanceClick() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "$",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        modifier = modifier.padding(end = 16.dp, start = 12.dp)
    )
}

@Preview
@Composable
fun ExpensesOverviewScreenPreview() {
    ExpensesTrackerTheme {
        ExpensesOverviewScreen(
            state = ExpensesOverviewState(),
            onAction = {},
            onBalanceClick = {},
            onEditClick = {},
            onAddExpenseClick = {},
            onDeleteExpense = {}
        )
    }
}