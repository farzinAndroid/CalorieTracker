package com.farzin.tracker_presentation.screens.tracker_overview_screen

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import com.farzin.core_ui.BarBackGround
import com.farzin.core_ui.CarbColor
import com.farzin.core_ui.FatColor
import com.farzin.core_ui.ProteinColor

@Composable
fun NutrientsBar(
    carbs: Int,
    protein: Int,
    calories: Int,
    fat: Int,
    calorieGoal: Int,
    modifier: Modifier = Modifier,
) {
    val barBackground = MaterialTheme.colorScheme.BarBackGround
    val fatColor = MaterialTheme.colorScheme.FatColor
    val proteinColor = MaterialTheme.colorScheme.ProteinColor
    val carbsColor = MaterialTheme.colorScheme.CarbColor
    val caloriesExceededColor = MaterialTheme.colorScheme.error
    val carbRatioWidth = remember {
        Animatable(0f)
    }
    val proteinRatioWidth = remember {
        Animatable(0f)
    }
    val fatRatioWidth = remember {
        Animatable(0f)
    }

    LaunchedEffect(carbs) {
        carbRatioWidth.animateTo(
            targetValue = ((carbs * 4f) / calorieGoal)
        )
    }

    LaunchedEffect(protein) {
        proteinRatioWidth.animateTo(
            targetValue = ((carbs * 4f) / calorieGoal)
        )
    }

    LaunchedEffect(fat) {
        fatRatioWidth.animateTo(
            targetValue = ((carbs * 9f) / calorieGoal)
        )
    }


    Canvas(modifier = modifier) {
        if (calories <= calorieGoal) {
            val carbsWidth = carbRatioWidth.value * this.size.width
            val proteinWidth = proteinRatioWidth.value * this.size.width
            val fatWidth = fatRatioWidth.value * this.size.width


            drawRoundRect(
                color = barBackground,
                size = this.size,
                cornerRadius = CornerRadius(100f)
            )

            drawRoundRect(
                color = fatColor,
                size = Size(
                    width = carbsWidth + proteinWidth + fatWidth,
                    height = this.size.height
                ),
                cornerRadius = CornerRadius(100f)
            )

            drawRoundRect(
                color = proteinColor,
                size = Size(
                    width = carbsWidth + proteinWidth,
                    height = this.size.height
                ),
                cornerRadius = CornerRadius(100f)
            )

            drawRoundRect(
                color = carbsColor,
                size = Size(
                    width = carbsWidth,
                    height = this.size.height
                ),
                cornerRadius = CornerRadius(100f)
            )
        }else{
            drawRoundRect(
                color = caloriesExceededColor,
                size = size,
                cornerRadius = CornerRadius(100f)
            )
        }
    }


}