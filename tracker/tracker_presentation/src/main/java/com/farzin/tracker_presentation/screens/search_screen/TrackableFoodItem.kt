package com.farzin.tracker_presentation.screens.search_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.farzin.core.R
import com.farzin.core_ui.DarkGray
import com.farzin.core_ui.LocalSpacing
import com.farzin.core_ui.TextBlack
import com.farzin.core_ui.TextWhite
import com.farzin.tracker_presentation.screens.tracker_overview_screen.NutrientInfo

@Composable
fun TrackableFoodItem(
    modifier: Modifier = Modifier,
    trackableFoodUIState: TrackableFoodUIState,
    onClick: () -> Unit,
    onAmountChange: (String) -> Unit,
    onTrack: () -> Unit,
) {

    val food = trackableFoodUIState.food

    val spacing = LocalSpacing.current

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .padding(spacing.extraSmall)
            .shadow(1.dp, shape = RoundedCornerShape(5.dp))
            .background(MaterialTheme.colorScheme.TextWhite)
            .clickable { onClick() }
            .padding(end = spacing.medium)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(food.imageUrl),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(topStart = 5.dp))
                )

                Spacer(modifier = Modifier.width(spacing.medium))

                Column(
                    modifier = Modifier
                        .align(CenterVertically)
                ) {
                    Text(
                        text = food.name,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.TextBlack,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(spacing.small))

                    Text(
                        text = stringResource(R.string.kcal_per_100g, food.caloriesPer100g),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.DarkGray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                }


            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.small)
            ) {
                NutrientInfo(
                    amount = food.carbsPer100g,
                    unit = stringResource(R.string.grams),
                    name = stringResource(R.string.carbs),
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.labelLarge,
                    amountColor = MaterialTheme.colorScheme.DarkGray
                )

                NutrientInfo(
                    amount = food.proteinsPer100g,
                    unit = stringResource(R.string.grams),
                    name = stringResource(R.string.protein),
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.labelLarge,
                    amountColor = MaterialTheme.colorScheme.DarkGray
                )

                NutrientInfo(
                    amount = food.fatPer100g,
                    unit = stringResource(R.string.grams),
                    name = stringResource(R.string.fat),
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.labelLarge,
                    amountColor = MaterialTheme.colorScheme.DarkGray
                )


            }
        }

        AnimatedVisibility(visible = trackableFoodUIState.isExpanded) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing.medium),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    BasicTextField(
                        value = trackableFoodUIState.amount,
                        onValueChange = onAmountChange,
                        keyboardOptions = KeyboardOptions(
                            imeAction = if (trackableFoodUIState.amount.isBlank()) {
                                ImeAction.Done
                            } else {
                                ImeAction.Default
                            },
                            keyboardType = KeyboardType.Number
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                onTrack()
                                defaultKeyboardAction(ImeAction.Done)
                            }
                        ),
                        singleLine = true,
                        modifier = Modifier
                            .border(
                                shape = RoundedCornerShape(5.dp),
                                width = 0.5.dp,
                                color = MaterialTheme.colorScheme.DarkGray
                            )
                            .alignBy(LastBaseline)
                            .padding(spacing.medium)
                    )

                    Spacer(modifier = Modifier.width(spacing.extraSmall))

                    Text(
                        text = stringResource(R.string.grams),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.TextBlack,
                        modifier = Modifier
                            .alignBy(LastBaseline)
                    )
                }


                IconButton(onClick = onTrack, enabled = trackableFoodUIState.amount.isNotBlank()) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = ""
                    )
                }

            }
        }

    }

}