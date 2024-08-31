package com.farzin.tracker_presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.farzin.core_ui.LocalSpacing
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

    Row(
        modifier = modifier
    ) {

        Text(
            text = amount.toString(),
            style = MaterialTheme.typography.displayLarge,
            fontSize = amountTextSize,
            color = amountColor,
            modifier = Modifier.alignBy(
                LastBaseline
            )
        )

        Spacer(modifier = Modifier.width(LocalSpacing.current.extraSmall))

        Text(
            text = unit,
            style = MaterialTheme.typography.bodyLarge,
            fontSize = unitTextSize,
            color = unitTextColor,
            modifier = Modifier.alignBy(
                LastBaseline
            )
        )
    }


}