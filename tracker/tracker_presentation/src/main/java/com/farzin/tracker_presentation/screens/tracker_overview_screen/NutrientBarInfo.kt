package com.farzin.tracker_presentation.screens.tracker_overview_screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.farzin.core.R
import com.farzin.core_ui.BarBackGround
import com.farzin.core_ui.LocalSpacing
import com.farzin.core_ui.TextWhite
import com.farzin.tracker_presentation.components.UnitDisplay


@Composable
fun NutrientBarInfo(
    modifier: Modifier = Modifier,
    value: Int,
    goal: Int,
    name: String,
    color: Color,
    strokeWidth: Dp = 8.dp,
) {
    val barBackground = MaterialTheme.colorScheme.BarBackGround
    val goalExceededColor = MaterialTheme.colorScheme.error
    val angleRatio = remember {
        Animatable(0f)
    }

    LaunchedEffect(value) {
        angleRatio.animateTo(
            targetValue = if (goal > 0) {
                value / goal.toFloat()
            } else 0f,
            animationSpec = tween(300)
        )
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            drawArc(
                color = if (value <= goal) barBackground else goalExceededColor,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                size = size,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )

            if (value <= goal) {
                drawArc(
                    color = color,
                    startAngle = 90f,
                    sweepAngle = 360f * angleRatio.value,
                    useCenter = false,
                    size = size,
                    style = Stroke(
                        width = strokeWidth.toPx(),
                        cap = StrokeCap.Round
                    )
                )
            }
        }

        Box(
            modifier = Modifier
                .padding(bottom = LocalSpacing.current.medium),
            contentAlignment = Alignment.Center
        ) {
            UnitDisplay(
                modifier = Modifier.wrapContentSize(),
                amount = value,
                unit = stringResource(R.string.grams),
                amountColor = if (value <= goal) MaterialTheme.colorScheme.TextWhite else goalExceededColor,
                unitTextColor = if (value <= goal) MaterialTheme.colorScheme.TextWhite else goalExceededColor
            )
        }

            Text(
                text = name,
                color = if (value <= goal) MaterialTheme.colorScheme.TextWhite else goalExceededColor,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = LocalSpacing.current.medium)
            )
    }


}