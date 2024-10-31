package com.elekiwi.expensestracker.expenses_overview.presentation.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import kotlin.random.Random

fun randomColor(minBrightness: Int = 90): Int {
    val random = Random.Default
    val red = random.nextInt(256)
    val blue = random.nextInt(256)
    val green = random.nextInt(256)
    return Color(red, green, blue).copy(alpha = 0.3f).toArgb()
}