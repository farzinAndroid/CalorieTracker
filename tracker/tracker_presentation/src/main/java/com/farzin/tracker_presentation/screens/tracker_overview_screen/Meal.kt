package com.farzin.tracker_presentation.screens.tracker_overview_screen

import androidx.annotation.DrawableRes
import com.farzin.core.util.UIText
import com.farzin.tracker_domain.model.MealType
import com.farzin.core.R

data class Meal(
    val name:UIText,
    @DrawableRes val drawableRes:Int,
    val mealType: MealType,
    val carbs:Int = 0,
    val protein:Int = 0,
    val fat:Int = 0,
    val calories:Int = 0,
    val isExpanded:Boolean = false,
)


val defaultMeal = listOf(
    Meal(
        name = UIText.StringResource(R.string.breakfast),
        drawableRes = R.drawable.ic_breakfast,
        mealType = MealType.BreakFast
    ),

    Meal(
        name = UIText.StringResource(R.string.dinner),
        drawableRes = R.drawable.ic_dinner,
        mealType = MealType.Dinner
    ),
    Meal(
        name = UIText.StringResource(R.string.lunch),
        drawableRes = R.drawable.ic_lunch,
        mealType = MealType.Lunch
    ),
    Meal(
        name = UIText.StringResource(R.string.snacks),
        drawableRes = R.drawable.ic_snack,
        mealType = MealType.Snack
    )
)
