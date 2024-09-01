package com.farzin.tracker_presentation.screens.search_screen

import com.farzin.tracker_domain.model.MealType
import com.farzin.tracker_domain.model.TrackableFood
import java.time.LocalDate

sealed class SearchEvent {

    data class OnQueryChanged(val query: String) : SearchEvent()
    data object OnSearch : SearchEvent()
    data class OnToggleTrackableFood(val food: TrackableFood) : SearchEvent()
    data class OnAmountForFoodChange(val amount: String, val food: TrackableFood) : SearchEvent()
    data class OnTrackedFoodClicked(
        val food: TrackableFood,
        val mealType: MealType,
        val date: LocalDate,
    ) : SearchEvent()
    data class OnSearchFocusChange(val isFocused:Boolean) : SearchEvent()

}