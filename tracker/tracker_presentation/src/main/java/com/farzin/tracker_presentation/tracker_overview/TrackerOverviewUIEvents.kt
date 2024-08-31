package com.farzin.tracker_presentation.tracker_overview

import com.farzin.tracker_domain.model.TrackedFood

sealed class TrackerOverviewUIEvents {

    data object OnNextDayClicked : TrackerOverviewUIEvents()
    data object OnPreviousDayClicked : TrackerOverviewUIEvents()
    data class OnToggleMealClicked(val meal:Meal): TrackerOverviewUIEvents()
    data class OnDeleteTrackedFoodClicked(val trackedFood: TrackedFood): TrackerOverviewUIEvents()
    data class OnAddFoodClicked(val meal:Meal): TrackerOverviewUIEvents()


}