package com.farzin.tracker_presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.farzin.core_ui.TextWhite

@Composable
fun UnitDisplay(
    amount: Int,
    unit: String,
    modifier: Modifier = Modifier,
    amountTextSize: TextUnit = 20.sp,
    amountColor: Color = MaterialTheme.colorScheme.TextWhite,
    unitTextSize: TextUnit = 14.sp,
    unitTextColor: Color = MaterialTheme.colorScheme.TextWhite,
) {


    Text(text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = amountColor,
                fontSize = amountTextSize
            )
        ) {
            append(amount.toString())
        }

        withStyle(
            style = SpanStyle(
                color = unitTextColor,
                fontSize = unitTextSize
            )
        ) {
            append(" $unit")
        }
    }, modifier = modifier)
}