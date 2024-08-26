package com.farzin.onboarding_presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.farzin.core_ui.LocalSpacing
import com.farzin.core_ui.brightGreen

@Composable
fun ActionButton(
    text:String,
    onClick:()->Unit,
    modifier: Modifier = Modifier,
    isEnabled:Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge
) {


    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = isEnabled,
        shape = RoundedCornerShape(100.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.brightGreen
        ),
        content = {
            Text(
                text = text,
                style = textStyle,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .padding(LocalSpacing.current.small)
            )
        }
    )
}