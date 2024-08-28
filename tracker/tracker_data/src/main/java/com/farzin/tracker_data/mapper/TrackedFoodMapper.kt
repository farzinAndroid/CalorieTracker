package com.farzin.tracker_data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.farzin.tracker_data.local.entity.TrackedFoodEntity
import com.farzin.tracker_domain.model.MealType
import com.farzin.tracker_domain.model.TrackedFood
import java.time.LocalDate



fun TrackedFoodEntity.toTrackedFood(): TrackedFood {
    return TrackedFood(
        name = this.name,
        imageUrl = this.imageUrl,
        id = this.id,
        date = LocalDate.of(this.year, this.month, this.dayOfMonth),
        fat = this.fat,
        carbs = this.carbs,
        amount = this.amount,
        calories = this.calories,
        mealType = MealType.fromString(this.type),
        protein = this.protein
    )
}



fun TrackedFood.toTrackedFoodEntity(): TrackedFoodEntity {
    return TrackedFoodEntity(
        name = this.name,
        imageUrl = this.imageUrl,
        id = this.id,
        fat = this.fat,
        carbs = this.carbs,
        amount = this.amount,
        calories = this.calories,
        type = this.mealType.name,
        protein = this.protein,
        dayOfMonth = this.date.dayOfMonth,
        year = this.date.year,
        month = this.date.monthValue,
    )
}