package com.farzin.tracker_data.mapper

import com.farzin.tracker_data.remote.dto.Product
import com.farzin.tracker_domain.model.TrackableFood
import kotlin.math.roundToInt


fun Product.toTrackableFood() : TrackableFood?{
    return TrackableFood(
        name = this.product_name ?: return null,
        carbsPer100g = this.nutriments.carbohydrates_100g.roundToInt(),
        fatPer100g = this.nutriments.fat_100g.roundToInt(),
        proteinsPer100g = this.nutriments.proteins_100g.roundToInt(),
        caloriesPer100g = this.nutriments.energyKcal100g.roundToInt(),
        imageUrl = this.image_front_thumb_url
    )
}