package com.farzin.tracker_presentation.screens.search_screen



data class SearchState(
    val query:String = "",
    val isHintVisible:Boolean = false,
    val isSearching:Boolean = false,
    val trackableFoods:List<TrackableFoodUIState> = emptyList(),
)
