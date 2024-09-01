package com.farzin.tracker_presentation.screens.search_screen

import com.farzin.tracker_domain.model.TrackableFood

data class TrackableFoodUIState(
    val food:TrackableFood,
    val isExpanded:Boolean = false,
    val amount:String = "",
)