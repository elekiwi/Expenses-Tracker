package com.elekiwi.expensestracker.balance.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elekiwi.expensestracker.core.presentation.ui.theme.ExpensesTrackerTheme
import com.elekiwi.expensestracker.core.presentation.ui.theme.montserrat
import com.elekiwi.expensestracker.core.presentation.util.Background
import org.koin.androidx.compose.koinViewModel

@Composable
fun BalanceScreenCore(
    viewModel: BalanceViewModel = koinViewModel(),
    onSaveClick: () -> Unit
) {
    BalanceScreen(
        state = viewModel.state,
        onAction = viewModel::onAction,
        onSaveClick = {
            viewModel.onAction(BalanceAction.OnBalanceSaved)
            onSaveClick()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BalanceScreen(
    state: BalanceState,
    onAction: (BalanceAction) -> Unit,
    onSaveClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(12.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent
                ),
                title = {
                    Text(
                        "Update Balance",
                        fontFamily = montserrat,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            )
        }
    ) { padding ->
        Background()

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(Modifier.height(24.dp))

            Text(
                text = "$${state.balance}",
                fontSize = 40.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(Modifier.height(38.dp))

            OutlinedTextField(
                value = state.balance.toString(),
                onValueChange = {
                    onAction(
                        BalanceAction.OnBalanceChanged(
                            it.toDoubleOrNull() ?: 0.0
                        )
                    )
                },
                label = {
                    Text("Enter balance")
                },
                textStyle = TextStyle(
                    fontSize = 18.sp
                ),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                modifier = Modifier.fillMaxWidth()

            )

            Spacer(Modifier.height(38.dp))

            OutlinedButton(
                onClick = { onSaveClick() }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Save balance",
                        modifier = Modifier.size(33.dp)
                    )

                    Spacer(Modifier.width(12.dp))
                    Text(
                        "Save Balance",
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun BalanceScreenPreview() {
    ExpensesTrackerTheme {
        BalanceScreen(
            state = BalanceState(),
            onAction = {},
            onSaveClick = {}
        )
    }
}