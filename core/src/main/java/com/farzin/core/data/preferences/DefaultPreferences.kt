package com.farzin.core.data.preferences

import android.content.SharedPreferences
import com.farzin.core.domain.model.ActivityLevel
import com.farzin.core.domain.model.Gender
import com.farzin.core.domain.model.GoalType
import com.farzin.core.domain.model.UserInfo
import com.farzin.core.domain.preferences.Preferences
import javax.inject.Inject

class DefaultPreferences @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : Preferences {
    override fun saveGender(gender: Gender) {
        sharedPreferences
            .edit()
            .putString(Preferences.KEY_GENDER, gender.name)
            .apply()
    }

    override fun saveAge(age: Int) {
        sharedPreferences
            .edit()
            .putInt(Preferences.KEY_AGE, age)
            .apply()
    }

    override fun saveWeight(weight: Float) {
        sharedPreferences
            .edit()
            .putFloat(Preferences.KEY_WEIGHT, weight)
            .apply()
    }

    override fun saveHeight(height: Int) {
        sharedPreferences
            .edit()
            .putInt(Preferences.KEY_HEIGHT, height)
            .apply()
    }

    override fun saveActivityLevel(activityLevel: ActivityLevel) {
        sharedPreferences
            .edit()
            .putString(Preferences.KEY_ACTIVITY_LEVEL, activityLevel.name)
            .apply()
    }

    override fun saveGoalType(goalType: GoalType) {
        sharedPreferences
            .edit()
            .putString(Preferences.KEY_GOAL_TYPE, goalType.name)
            .apply()
    }

    override fun saveCarbRatio(ratio: Float) {
        sharedPreferences
            .edit()
            .putFloat(Preferences.KEY_CARB_RATIO, ratio)
            .apply()
    }

    override fun saveProteinRatio(ratio: Float) {
        sharedPreferences
            .edit()
            .putFloat(Preferences.KEY_PROTEIN_RATIO, ratio)
            .apply()
    }

    override fun saveFatRatio(ratio: Float) {
        sharedPreferences
            .edit()
            .putFloat(Preferences.KEY_FAT_RATIO, ratio)
            .apply()
    }

    override fun saveShouldShowOnBoarding(value: Boolean) {
        sharedPreferences
            .edit()
            .putBoolean(Preferences.KEY_SHOULD_SHOW_ON_BOARDING, value)
            .apply()
    }

    override fun loadShouldShowOnBoarding() : Boolean {
        return sharedPreferences.getBoolean(Preferences.KEY_SHOULD_SHOW_ON_BOARDING,true)
    }

    override fun loadUserInfo(): UserInfo {
        val age = sharedPreferences.getInt(Preferences.KEY_AGE, -1)
        val weight = sharedPreferences.getFloat(Preferences.KEY_WEIGHT, -1f)
        val height = sharedPreferences.getInt(Preferences.KEY_HEIGHT, -1)
        val gender = sharedPreferences.getString(Preferences.KEY_GENDER, null)
        val goalType = sharedPreferences.getString(Preferences.KEY_GOAL_TYPE, null)
        val activityLevel = sharedPreferences.getString(Preferences.KEY_ACTIVITY_LEVEL, null)
        val carbRatio = sharedPreferences.getFloat(Preferences.KEY_CARB_RATIO, -1f)
        val proteinRatio = sharedPreferences.getFloat(Preferences.KEY_PROTEIN_RATIO, -1f)
        val fatRatio = sharedPreferences.getFloat(Preferences.KEY_FAT_RATIO, -1f)


        return UserInfo(
            gender = Gender.fromString(gender ?: "male"),
            age = age,
            height = height,
            weight = weight,
            goalType = GoalType.fromString(goalType ?: "keep_weight"),
            fatRatio = fatRatio,
            carbRatio = carbRatio,
            proteinRatio = proteinRatio,
            activityLevel = ActivityLevel.fromString(activityLevel ?: "medium")

        )


    }
}