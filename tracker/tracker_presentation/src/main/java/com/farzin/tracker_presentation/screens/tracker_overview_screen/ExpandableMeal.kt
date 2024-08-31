package com.farzin.tracker_presentation.screens.tracker_overview_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.farzin.core.R
import com.farzin.core_ui.DarkGray
import com.farzin.core_ui.LocalSpacing
import com.farzin.core_ui.TextBlack
import com.farzin.tracker_presentation.components.UnitDisplay

@Composable
fun ExpandableMeal(
    modifier: Modifier = Modifier,
    meal: Meal,
    content: @Composable () -> Unit,
    onToggleClick: () -> Unit,
) {

    val context = LocalContext.current

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onToggleClick() }
                .padding(LocalSpacing.current.medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(meal.drawableRes),
                contentDescription = meal.name.asString(context)
            )

            Spacer(modifier = Modifier.width(LocalSpacing.current.medium))

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = meal.name.asString(context),
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Icon(
                        imageVector = if (meal.isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = "",
                    )
                }


                Spacer(modifier = Modifier.height(LocalSpacing.current.small))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    UnitDisplay(
                        amount = meal.calories,
                        unit = stringResource(R.string.kcal),
                        amountTextSize = 30.sp,
                        amountColor = MaterialTheme.colorScheme.TextBlack,
                        unitTextColor = MaterialTheme.colorScheme.DarkGray
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(LocalSpacing.current.medium)
                    ) {
                        NutrientInfo(
                            amount = meal.carbs,
                            unit = stringResource(R.string.grams),
                            name = stringResource(R.string.carbs),
                            unitTextColor = MaterialTheme.colorScheme.DarkGray,
                            amountColor = MaterialTheme.colorScheme.TextBlack
                        )

                        NutrientInfo(
                            amount = meal.protein,
                            unit = stringResource(R.string.grams),
                            name = stringResource(R.string.protein),
                            unitTextColor = MaterialTheme.colorScheme.DarkGray,
                            amountColor = MaterialTheme.colorScheme.TextBlack
                        )

                        NutrientInfo(
                            amount = meal.fat,
                            unit = stringResource(R.string.grams),
                            name = stringResource(R.string.fat),
                            unitTextColor = MaterialTheme.colorScheme.DarkGray,
                            amountColor = MaterialTheme.colorScheme.TextBlack
                        )
                    }

                }

            }


        }

        Spacer(modifier = Modifier.height(LocalSpacing.current.medium))

        AnimatedVisibility(visible = meal.isExpanded) {
            content()
        }
    }

}