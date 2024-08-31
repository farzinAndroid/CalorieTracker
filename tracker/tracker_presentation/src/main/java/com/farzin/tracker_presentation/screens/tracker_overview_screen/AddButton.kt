package com.farzin.tracker_presentation.screens.tracker_overview_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.farzin.core_ui.BrightGreen
import com.farzin.core_ui.LocalSpacing


@Composable
fun AddButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    color: Color = MaterialTheme.colorScheme.BrightGreen,
) {


    Row(
        modifier = modifier
            .clip(RoundedCornerShape(100f))
            .clickable { onClick() }
            .border(
                width = 1.dp,
                color = color,
                shape = RoundedCornerShape(100f)
            )
            .padding(LocalSpacing.current.medium),
        horizontalArrangement = Arrangement.Absolute.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "",
            tint = color
        )

        Spacer(modifier = Modifier.width(LocalSpacing.current.medium))

        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = color
        )

    }

}