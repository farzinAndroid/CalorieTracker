package com.farzin.tracker_presentation.screens.tracker_overview_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.farzin.core.R
import com.farzin.core.util.UIEvent
import com.farzin.core_ui.LocalSpacing
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TrackerOverviewScreen(
    onNavigateToSearch: (String,Int,Int,Int) -> Unit,
    viewmodel: TrackerOverviewViewmodel = hiltViewModel(),
) {

    val state = viewmodel.state
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = LocalSpacing.current.medium)
    ) {
        item {
            NutrientsHeaderSection(state = state)
        }

        item {
            Spacer(modifier = Modifier.height(LocalSpacing.current.medium))

            DaySelectorSection(
                date = state.date,
                onPrevDayClicked = { viewmodel.onEvent(TrackerOverviewUIEvents.OnPreviousDayClicked) },
                onNextDayClicked = { viewmodel.onEvent(TrackerOverviewUIEvents.OnNextDayClicked) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = LocalSpacing.current.medium)
            )

            Spacer(modifier = Modifier.height(LocalSpacing.current.medium))
        }

        items(state.meals) { meal ->
            ExpandableMeal(
                meal = meal,
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = LocalSpacing.current.small),
                    ) {
                        val filteredFoods = state.trackedFoods.filter {
                            it.mealType == meal.mealType
                        }
                        filteredFoods.forEach { food ->
                            TrackedFoodItem(
                                trackedFood = food,
                                onDeleteClicked = {
                                    viewmodel.onEvent(
                                        TrackerOverviewUIEvents.OnDeleteTrackedFoodClicked(
                                            food
                                        )
                                    )
                                },
                            )
                            Spacer(modifier = Modifier.height(LocalSpacing.current.medium))

                        }



                        AddButton(
                            text = stringResource(
                                R.string.add_meal,
                                meal.name.asString(context)
                            ),
                            onClick = {
                                onNavigateToSearch(
                                    meal.name.asString(context),
                                    state.date.dayOfMonth,
                                    state.date.monthValue,
                                    state.date.year
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                onToggleClick = { viewmodel.onEvent(TrackerOverviewUIEvents.OnToggleMealClicked(meal)) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}