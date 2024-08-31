package com.farzin.tracker_presentation.screens.tracker_overview_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.farzin.core.R
import com.farzin.core_ui.DarkGray
import com.farzin.core_ui.LocalSpacing
import com.farzin.core_ui.TextBlack
import com.farzin.tracker_domain.model.MealType
import com.farzin.tracker_domain.model.TrackedFood
import java.time.LocalDate


@Composable
fun TrackedFoodItem(
    modifier: Modifier = Modifier,
    trackedFood: TrackedFood,
    onDeleteClicked: () -> Unit,
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .padding(spacing.extraSmall)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(5.dp)
            )
            .background(Color.White)
            .padding(end = spacing.medium)
            .height(100.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = trackedFood.imageUrl,
                onState = {
                    when (it) {
                        is AsyncImagePainter.State.Error -> {
                            R.drawable.ic_burger
                        }

                        else -> {}
                    }
                },
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(topStart = 5.dp, bottomStart = 5.dp))
        )
        Spacer(modifier = Modifier.width(spacing.medium))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = trackedFood.name,
                style = MaterialTheme.typography.bodyLarge,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(spacing.extraSmall))
            Text(
                text = stringResource(
                    id = R.string.nutrient_info,
                    trackedFood.amount,
                    trackedFood.calories
                ),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.width(spacing.medium))
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(id = R.string.delete),
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable { onDeleteClicked() }
            )
            Spacer(modifier = Modifier.height(spacing.extraSmall))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(spacing.medium)
            ) {
                NutrientInfo(
                    name = stringResource(id = R.string.carbs),
                    amount = trackedFood.carbs,
                    unit = stringResource(id = R.string.grams),
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.bodyMedium,
                    unitTextColor = MaterialTheme.colorScheme.DarkGray,
                    amountColor = MaterialTheme.colorScheme.TextBlack
                )
                NutrientInfo(
                    name = stringResource(id = R.string.protein),
                    amount = trackedFood.protein,
                    unit = stringResource(id = R.string.grams),
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.bodyMedium,
                    unitTextColor = MaterialTheme.colorScheme.DarkGray,
                    amountColor = MaterialTheme.colorScheme.TextBlack
                )
                NutrientInfo(
                    name = stringResource(id = R.string.fat),
                    amount = trackedFood.fat,
                    unit = stringResource(id = R.string.grams),
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.bodyMedium,
                    unitTextColor = MaterialTheme.colorScheme.DarkGray,
                    amountColor = MaterialTheme.colorScheme.TextBlack
                )
            }
        }
    }
}




@Preview
@Composable
private fun TrackedFoodItemPrev() {
    TrackedFoodItem(
        trackedFood = TrackedFood(
            "Kebab",
            10,
            10,
            10,
            null,
            MealType.Dinner,
            10,
            LocalDate.now(),
            10,
            1
        )
    ) {

    }
}