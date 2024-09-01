package com.farzin.tracker_presentation.screens.tracker_overview_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.core.domain.preferences.Preferences
import com.farzin.core.navigation.Route
import com.farzin.core.util.UIEvent
import com.farzin.tracker_domain.model.TrackedFood
import com.farzin.tracker_domain.use_case.TrackerUseCasesWrapperClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TrackerOverviewViewmodel @Inject constructor(
    private val preferences: Preferences,
    private val trackerUseCasesWrapperClass: TrackerUseCasesWrapperClass
) : ViewModel() {

    var state by mutableStateOf(TrackerOverviewState())

    private var getFoodsForDayJob : Job? = null

    init {
        refreshFoods()
        preferences.saveShouldShowOnBoarding(false)
    }

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: TrackerOverviewUIEvents){
        when(event){
            is TrackerOverviewUIEvents.OnAddFoodClicked -> {
                viewModelScope.launch {
                    _uiEvent.send(
                        UIEvent.Navigate(
                            route = Route.SEARCH +
                                    "/${event.meal.mealType.name}"+
                                    "/${state.date.dayOfMonth}"+
                                    "/${state.date.monthValue}"+
                                    "/${state.date.year}"
                        )
                    )
                }
            }
            is TrackerOverviewUIEvents.OnDeleteTrackedFoodClicked -> {
                viewModelScope.launch {
                    trackerUseCasesWrapperClass.deleteTrackFoodUseCase.invoke(event.trackedFood)
                    refreshFoods()
                }
            }
            is TrackerOverviewUIEvents.OnToggleMealClicked -> {
                state = state.copy(
                    meals = state.meals.map {
                        if (it.name == event.meal.name){
                            it.copy(isExpanded = !it.isExpanded)
                        }else{
                            it
                        }
                    }
                )
            }
            TrackerOverviewUIEvents.OnNextDayClicked -> {
                state = state.copy(
                    date = state.date.plusDays(1)
                )
                refreshFoods()
            }
            TrackerOverviewUIEvents.OnPreviousDayClicked -> {
                state = state.copy(
                    date = state.date.minusDays(1)
                )
                refreshFoods()
            }
        }
    }


    fun refreshFoods(){
        getFoodsForDayJob?.cancel()
        getFoodsForDayJob = trackerUseCasesWrapperClass.getFoodForDateUseCase
                .invoke(state.date)
                .onEach {foods :  List<TrackedFood>->
                    val nutrientsResult = trackerUseCasesWrapperClass.calculateMealNutrientsUseCase.invoke(foods)
                    state = state.copy(
                        caloriesGoal = nutrientsResult.caloriesGoal,
                        fatGoal = nutrientsResult.fatGoal,
                        carbsGoal = nutrientsResult.carbsGoal,
                        proteinGoal = nutrientsResult.proteinGoal,
                        totalFat = nutrientsResult.totalFatAte,
                        totalProtein = nutrientsResult.totalProteinAte,
                        totalCalories = nutrientsResult.totalCaloriesAte,
                        totalCarbs = nutrientsResult.totalCarbsAte,
                        trackedFoods = foods,
                        meals = state.meals.map {
                            val nutrientsForMeal =
                                nutrientsResult.mealNutrients[it.mealType]
                                    ?: return@map it.copy(
                                        carbs = 0,
                                        protein = 0,
                                        fat = 0,
                                        calories = 0
                                    )
                            it.copy(
                                carbs = nutrientsForMeal.carbs,
                                protein = nutrientsForMeal.protein,
                                fat = nutrientsForMeal.fat,
                                calories = nutrientsForMeal.calories
                            )
                        }
                    )
                }
                .launchIn(viewModelScope)

        }

}