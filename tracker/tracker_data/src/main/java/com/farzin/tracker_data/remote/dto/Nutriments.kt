package com.farzin.tracker_data.remote.dto

import com.google.gson.annotations.SerializedName

data class Nutriments(
    val carbohydrates_100g: Double,
    @SerializedName("energy-kcal_100g")
    val energyKcal100g: Double,
    val fat_100g: Double,
    val proteins_100g: Double
)