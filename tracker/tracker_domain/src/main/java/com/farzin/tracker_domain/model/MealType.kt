package com.farzin.tracker_domain.model

sealed class MealType(val name:String) {

    data object BreakFast : MealType(name = "breakfast")
    data object Lunch : MealType(name = "lunch")
    data object Dinner : MealType(name = "dinner")
    data object Snack : MealType(name = "snack")

    companion object{
        fun fromString(name: String):MealType{
            return when(name.lowercase() ){
                "breakfast"->BreakFast
                "lunch"->Lunch
                "dinner"->Dinner
                "snack"->Snack
                else -> BreakFast
            }
        }
    }

}