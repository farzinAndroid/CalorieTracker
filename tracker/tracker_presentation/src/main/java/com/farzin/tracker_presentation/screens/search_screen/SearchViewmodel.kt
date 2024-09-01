package com.farzin.tracker_presentation.screens.search_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.core.domain.use_case.FilterOutDigitsUseCase
import com.farzin.core.util.UIEvent
import com.farzin.core.util.UIText
import com.farzin.tracker_domain.use_case.TrackerUseCasesWrapperClass
import com.farzin.core.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewmodel @Inject constructor(
    private val trackerUseCasesWrapperClass: TrackerUseCasesWrapperClass,
    private val filterOutDigitsUseCase: FilterOutDigitsUseCase
) : ViewModel() {

    var state by mutableStateOf(SearchState())
        private set

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SearchEvent){
        when(event){
            is SearchEvent.OnQueryChanged -> {
                state = state.copy(query = event.query)
            }
            is SearchEvent.OnAmountForFoodChange -> {
                state = state.copy(
                    trackableFoods = state.trackableFoods.map {trackableFoodUIState ->
                        if (trackableFoodUIState.food == event.food){
                            trackableFoodUIState.copy(amount = filterOutDigitsUseCase.invoke(event.amount))
                        }else{
                            trackableFoodUIState
                        }
                    }
                )
            }
            SearchEvent.OnSearch -> {
                executeSearch()
            }
            is SearchEvent.OnToggleTrackableFood -> {
                state = state.copy(
                    trackableFoods = state.trackableFoods.map {trackableFoodUIState ->
                        if (trackableFoodUIState.food == event.food){
                            trackableFoodUIState.copy(isExpanded = !trackableFoodUIState.isExpanded)
                        }else{
                            trackableFoodUIState
                        }
                    }
                )
            }
            is SearchEvent.OnSearchFocusChange -> {
                state = state.copy(
                    isHintVisible = !event.isFocused && state.query.isBlank()
                )
            }
            is SearchEvent.OnTrackedFoodClicked -> {
                trackFood(event)
            }
        }
    }

    private fun executeSearch() {
        viewModelScope.launch {
            state = state.copy(
                isSearching = true,
                trackableFoods = emptyList()
            )

            trackerUseCasesWrapperClass.searchFoodUseCase
                .invoke(state.query)
                .onSuccess {foodsList->
                    state = state.copy(
                        isSearching = false,
                        trackableFoods = foodsList.map {
                            TrackableFoodUIState(it)
                        },
                        query = ""
                    )
                }
                .onFailure {
                    state = state.copy(
                        isSearching = false
                    )
                    _uiEvent.send(UIEvent.ShowSnackBar(UIText.StringResource(R.string.error_something_went_wrong)))
                }
        }
    }

    private fun trackFood(event: SearchEvent.OnTrackedFoodClicked) {
        viewModelScope.launch {
            val uiState = state.trackableFoods.find { it.food == event.food }

            trackerUseCasesWrapperClass.insertTrackFoodUseCase.invoke(
                trackableFood = uiState?.food ?: return@launch,
                amount = uiState.amount.toIntOrNull() ?: return@launch,
                mealType = event.mealType,
                date = event.date
            )

            _uiEvent.send(UIEvent.NavigateUp)

        }
    }

}