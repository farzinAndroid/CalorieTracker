package com.farzin.core.domain.preferences

import com.farzin.core.domain.model.ActivityLevel
import com.farzin.core.domain.model.Gender
import com.farzin.core.domain.model.GoalType
import com.farzin.core.domain.model.UserInfo

interface Preferences {

    fun saveGender(gender: Gender)
    fun saveAge(age:Int)
    fun saveWeight(weight:Float)
    fun saveHeight(height:Int)
    fun saveActivityLevel(activityLevel: ActivityLevel)
    fun saveGoalType(goalType: GoalType)
    fun saveCarbRatio(ratio:Float)
    fun saveProteinRatio(ratio:Float)
    fun saveFatRatio(ratio:Float)

    fun loadUserInfo() : UserInfo

    companion object{
        const val KEY_GENDER = "KEY_GENDER"
        const val KEY_AGE = "KEY_AGE"
        const val KEY_WEIGHT = "KEY_WEIGHT"
        const val KEY_HEIGHT = "KEY_HEIGHT"
        const val KEY_ACTIVITY_LEVEL = "KEY_ACTIVITY_LEVEL"
        const val KEY_GOAL_TYPE = "KEY_GOAL_TYPE"
        const val KEY_CARB_RATIO = "KEY_CARB_RATIO"
        const val KEY_PROTEIN_RATIO = "KEY_PROTEIN_RATIO"
        const val KEY_FAT_RATIO = "KEY_FAT_RATIO"
    }

}