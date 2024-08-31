package com.farzin.tracker_domain.use_case

import com.farzin.tracker_domain.model.MealType
import com.farzin.tracker_domain.model.TrackableFood
import com.farzin.tracker_domain.model.TrackedFood
import com.farzin.tracker_domain.repository.TrackerRepository
import java.time.LocalDate
import kotlin.math.roundToInt

class InsertTrackFoodUseCase(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(
        trackableFood: TrackableFood,
        amount: Int,
        mealType: MealType,
        date:LocalDate
    ){
        repository.insertTrackedFood(
            trackedFood = TrackedFood(
                name = trackableFood.name,
                protein = ((trackableFood.proteinsPer100g / 100f) * amount).roundToInt(),
                fat = ((trackableFood.fatPer100g / 100f) * amount).roundToInt(),
                carbs = ((trackableFood.carbsPer100g / 100f) * amount).roundToInt(),
                calories = ((trackableFood.caloriesPer100g / 100f) * amount).roundToInt(),
                imageUrl = trackableFood.imageUrl,
                date = date,
                mealType = mealType,
                amount = amount,
            )
        )
    }

}