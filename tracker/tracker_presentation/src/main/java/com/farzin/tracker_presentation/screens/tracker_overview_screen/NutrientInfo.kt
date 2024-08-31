package com.farzin.tracker_presentation.screens.tracker_overview_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.farzin.core_ui.BrightGreen
import com.farzin.core_ui.MediumGray
import com.farzin.core_ui.TextWhite
import com.farzin.tracker_presentation.components.UnitDisplay

@Composable
fun NutrientInfo(
    amount: Int,
    unit: String,
    modifier: Modifier = Modifier,
    amountTextSize: TextUnit = 20.sp,
    amountColor: Color = MaterialTheme.colorScheme.TextWhite,
    unitTextSize: TextUnit = 14.sp,
    unitTextColor: Color = MaterialTheme.colorScheme.TextWhite,
    nameTextStyle:TextStyle = MaterialTheme.typography.bodyLarge,
    name:String,
) {


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UnitDisplay(
            amount = amount,
            unit = unit,
            amountTextSize = amountTextSize,
            unitTextColor = unitTextColor,
            amountColor = amountColor,
            unitTextSize = unitTextSize
        )


        Text(
            text = name,
            color = MaterialTheme.colorScheme.MediumGray,
            style = nameTextStyle,
            fontWeight = FontWeight.Light
        )




    }
}