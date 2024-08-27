package com.farzin.onboarding_presentation.screens.nutrient_goal_screen

sealed class NutrientGoalEvent {

    data class OnCarbRatioEnter(val ratio:String) : NutrientGoalEvent()
    data class OnProteinRatioEnter(val ratio:String) : NutrientGoalEvent()
    data class OnFatRatioEnter(val ratio:String) : NutrientGoalEvent()
    data object OnNextClicked : NutrientGoalEvent()

}