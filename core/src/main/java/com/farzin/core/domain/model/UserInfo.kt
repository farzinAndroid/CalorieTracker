package com.farzin.core.domain.model

data class UserInfo(
    val age: Int,
    val weight:Float,
    val gender: Gender,
    val height:Int,
    val activityLevel: ActivityLevel,
    val goalType: GoalType,
    val carbRatio:Float,
    val fatRatio:Float,
    val proteinRatio:Float,
)
