package com.farzin.tracker_presentation.screens.tracker_overview_screen

import com.farzin.tracker_domain.model.TrackedFood

sealed class TrackerOverviewUIEvents {

    data object OnNextDayClicked : TrackerOverviewUIEvents()
    data object OnPreviousDayClicked : TrackerOverviewUIEvents()
    data class OnToggleMealClicked(val meal: Meal): TrackerOverviewUIEvents()
    data class OnDeleteTrackedFoodClicked(val trackedFood: TrackedFood): TrackerOverviewUIEvents()


}