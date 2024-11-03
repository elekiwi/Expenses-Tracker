package com.elekiwi.expensestracker.expenses_details.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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

fun ExpensesDetailsScreenCore(
    viewModel: ExpensesDetailsViewModel = koinViewModel(),
    onSaveExpense: () -> Unit,
    id: Int
) {

    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.event.collect { event ->
            when (event) {
                ExpensesDetailsEvent.SaveFailed -> {
                    Toast.makeText(context, "Failed to save expense", Toast.LENGTH_LONG).show()
                }

                ExpensesDetailsEvent.SaveSuccess -> {
                    onSaveExpense()
                }
            }
        }
    }

    ExpensesDetailsScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExpensesDetailsScreen(
    state: ExpensesDetailsState,
    onAction: (ExpensesDetailsAction) -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent
                ),
                title = {
                    Text(
                        "Add expense",
                        fontFamily = montserrat,
                        fontSize = 25.sp,
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
                            .clickable { onAction(ExpensesDetailsAction.SaveExpense) },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Save",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(30.dp)

                        )
                    }
                },
                modifier = Modifier.padding(end = 16.dp, start = 8.dp)
            )
        }
    ) { padding ->

        Background()

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Spacer(Modifier.height(50.dp))

            OutlinedTextField(
                value = state.name,
                onValueChange = { onAction(ExpensesDetailsAction.UpdateName(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                label = { Text("Name", fontWeight = FontWeight.Medium) },
                textStyle = TextStyle(
                    fontFamily = montserrat,
                    fontSize = 17.sp,
                ),
                maxLines = 1
            )

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = state.price.toString(),
                onValueChange = {
                    onAction(
                        ExpensesDetailsAction.UpdatePrice(
                            it.toDoubleOrNull() ?: 0.0
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                label = { Text("Price", fontWeight = FontWeight.Medium) },
                textStyle = TextStyle(
                    fontFamily = montserrat,
                    fontSize = 17.sp,
                ),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                )
            )

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                OutlinedTextField(
                    value = state.kilograms.toString(),
                    onValueChange = {
                        onAction(
                            ExpensesDetailsAction.UpdateKilograms(
                                it.toDoubleOrNull() ?: 0.0
                            )
                        )
                    },
                    modifier = Modifier
                        .weight(1f),
                    label = { Text("Kilograms", fontWeight = FontWeight.Medium) },
                    textStyle = TextStyle(
                        fontFamily = montserrat,
                        fontSize = 17.sp,
                    ),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )

                Spacer(Modifier.width(8.dp))

                OutlinedTextField(
                    value = state.quantity.toString(),
                    onValueChange = {
                        onAction(
                            ExpensesDetailsAction.UpdateQuantity(
                                it.toDoubleOrNull() ?: 0.0
                            )
                        )
                    },
                    modifier = Modifier
                        .weight(1f),
                    label = { Text("Quantity", fontWeight = FontWeight.Medium) },
                    textStyle = TextStyle(
                        fontFamily = montserrat,
                        fontSize = 17.sp,
                    ),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun ExpensesDetailsScreenPreview() {
    ExpensesTrackerTheme {
        ExpensesDetailsScreen(
            state = (ExpensesDetailsState()),
            onAction = {}
        )
    }
}