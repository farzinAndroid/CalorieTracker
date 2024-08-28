package com.farzin.tracker_data.mapper

import com.farzin.tracker_data.remote.dto.Product
import com.farzin.tracker_domain.model.TrackableFood
import kotlin.math.roundToInt


fun Product.toTrackableFood() : TrackableFood?{
    return TrackableFood(
        name = this.productName ?: return null,
        carbsPer100g = this.nutriments.carbohydrates100g.roundToInt(),
        fatPer100g = this.nutriments.fat100g.roundToInt(),
        proteinsPer100g = this.nutriments.proteins100g.roundToInt(),
        caloriesPer100g = this.nutriments.energyKcal100g.roundToInt(),
        imageUrl = this.imageFrontThumbUrl
    )
}