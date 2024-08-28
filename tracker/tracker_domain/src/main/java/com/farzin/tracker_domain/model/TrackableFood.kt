package com.farzin.tracker_domain.model

data class TrackableFood(
    val carbsPer100g: Int,
    val fatPer100g: Int,
    val proteinsPer100g: Int,
    val caloriesPer100g: Int,
    val name: String,
    val imageUrl: String?,
)
