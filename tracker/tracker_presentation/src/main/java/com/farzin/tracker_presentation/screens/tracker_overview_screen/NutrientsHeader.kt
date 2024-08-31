package com.farzin.tracker_presentation.screens.tracker_overview_screen

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farzin.core.R
import com.farzin.core_ui.BrightGreen
import com.farzin.core_ui.CarbColor
import com.farzin.core_ui.FatColor
import com.farzin.core_ui.LocalSpacing
import com.farzin.core_ui.ProteinColor
import com.farzin.core_ui.TextWhite
import com.farzin.tracker_presentation.components.UnitDisplay

@Composable
fun NutrientsHeader(
    modifier: Modifier = Modifier,
    state: TrackerOverviewState,
) {

    val animatedCalorieCount = animateIntAsState(targetValue = state.totalCalories, label = "")


    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
            .background(MaterialTheme.colorScheme.BrightGreen)
            .padding(
                horizontal = LocalSpacing.current.large,
                vertical = LocalSpacing.current.extraLarge,
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            UnitDisplay(
                amount = animatedCalorieCount.value,
                unit = stringResource(R.string.kcal),
                amountColor = MaterialTheme.colorScheme.TextWhite,
                amountTextSize = 40.sp,
                unitTextColor = MaterialTheme.colorScheme.TextWhite,
                modifier = Modifier.align(Alignment.Bottom)
            )


            Column {
                Text(
                    text = stringResource(R.string.your_goal),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.TextWhite,

                    )

                UnitDisplay(
                    amount = animatedCalorieCount.value,
                    unit = stringResource(R.string.kcal),
                    amountColor = MaterialTheme.colorScheme.TextWhite,
                    amountTextSize = 40.sp,
                    unitTextColor = MaterialTheme.colorScheme.TextWhite,
                )
            }
        }

        Spacer(modifier = Modifier.height(LocalSpacing.current.small))

        NutrientsBar(
            carbs = state.totalCarbs,
            protein = state.totalProtein,
            calories = state.totalCalories,
            fat = state.totalFat,
            calorieGoal = state.caloriesGoal,
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        )

        Spacer(modifier = Modifier.height(LocalSpacing.current.large))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            NutrientBarInfo(
                value = state.totalCarbs,
                goal = state.carbsGoal,
                name = stringResource(R.string.carbs),
                color = MaterialTheme.colorScheme.CarbColor,
                modifier = Modifier.size(90.dp)
            )

            NutrientBarInfo(
                value = state.totalProtein,
                goal = state.proteinGoal,
                name = stringResource(R.string.protein),
                color = MaterialTheme.colorScheme.ProteinColor,
                modifier = Modifier.size(90.dp)
            )

            NutrientBarInfo(
                value = state.totalFat,
                goal = state.fatGoal,
                name = stringResource(R.string.fat),
                color = MaterialTheme.colorScheme.FatColor,
                modifier = Modifier.size(90.dp)
            )
        }

    }


}