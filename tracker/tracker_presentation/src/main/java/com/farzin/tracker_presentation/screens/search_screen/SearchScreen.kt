package com.farzin.tracker_presentation.screens.search_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.farzin.core.R
import com.farzin.core.util.UIEvent
import com.farzin.core_ui.LocalSpacing
import com.farzin.core_ui.TextBlack
import com.farzin.tracker_domain.model.MealType
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDate

@Composable
fun SearchScreen(
    snackbarHostState: SnackbarHostState,
    onNavigateUp: () -> Unit,
    mealName: String,
    dayOfMonth: Int,
    month: Int,
    year: Int,
    viewmodel: SearchViewmodel = hiltViewModel(),
) {

    val spacing = LocalSpacing.current
    val state = viewmodel.state
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(keyboardController) {
        viewmodel.uiEvent.collectLatest {
            when (it) {
                UIEvent.NavigateUp -> {
                    onNavigateUp()
                }

                is UIEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(message = it.message.asString(context))
                    keyboardController?.hide()
                }

                is UIEvent.Navigate -> {}
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.medium)
    ) {
        Text(
            text = stringResource(R.string.add_meal, mealName),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.TextBlack
        )

        Spacer(modifier = Modifier.height(spacing.medium))

        SearchTextField(
            text = state.query,
            onValueChanged = {
                viewmodel.onEvent(SearchEvent.OnQueryChanged(it))
            },
            onSearch = { viewmodel.onEvent(SearchEvent.OnSearch) },
            onFocusChanged = {
                viewmodel.onEvent(SearchEvent.OnSearchFocusChange(it.isFocused))
            },
        )

        Spacer(modifier = Modifier.height(spacing.medium))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(state.trackableFoods) { food ->
                TrackableFoodItem(
                    trackableFoodUIState = food,
                    onClick = {
                        viewmodel.onEvent(SearchEvent.OnToggleTrackableFood(food.food))
                    },
                    onAmountChange = {
                        viewmodel.onEvent(
                            SearchEvent.OnAmountForFoodChange(
                                food = food.food,
                                amount = it
                            )
                        )
                    },
                    onTrack = {
                        viewmodel.onEvent(
                            SearchEvent.OnTrackedFoodClicked(
                                food = food.food,
                                mealType = MealType.fromString(mealName),
                                date = LocalDate.of(year, month, dayOfMonth)
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }

    }

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        when {
            state.isSearching -> CircularProgressIndicator()
            state.trackableFoods.isEmpty() -> {
                Text(
                    text = stringResource(R.string.no_results),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }


}