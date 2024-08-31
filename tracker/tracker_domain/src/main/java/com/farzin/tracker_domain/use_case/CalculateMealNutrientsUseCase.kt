package com.farzin.tracker_domain.use_case

import com.farzin.core.domain.model.ActivityLevel
import com.farzin.core.domain.model.Gender
import com.farzin.core.domain.model.GoalType
import com.farzin.core.domain.model.UserInfo
import com.farzin.core.domain.preferences.Preferences
import com.farzin.tracker_domain.model.MealType
import com.farzin.tracker_domain.model.TrackedFood
import kotlin.math.roundToInt

class CalculateMealNutrientsUseCase(
    private val preferences: Preferences
) {

    operator fun invoke(trackedFood: List<TrackedFood>) : Result{
        val allNutrients = trackedFood
            .groupBy { it.mealType }
            .mapValues {entry: Map.Entry<MealType, List<TrackedFood>>->
                val mealType = entry.key
                val foodsList = entry.value
                MealNutrients(
                    mealType = mealType,
                    carbs = foodsList.sumOf { it.carbs },
                    calories = foodsList.sumOf { it.calories },
                    fat = foodsList.sumOf { it.fat },
                    protein = foodsList.sumOf { it.protein },
                )
            }

        val totalCarbsAte = allNutrients.values.sumOf { it.carbs }
        val totalFatAte = allNutrients.values.sumOf { it.fat }
        val totalProteinAte = allNutrients.values.sumOf { it.protein }
        val totalCaloriesAte = allNutrients.values.sumOf { it.calories }

        val userInfo = preferences.loadUserInfo()

        val caloriesGoal = dailyCaloryRequirement(userInfo)
        val carbsGoal = (caloriesGoal * userInfo.carbRatio / 4f).roundToInt()
        val proteinGoal = (caloriesGoal * userInfo.proteinRatio / 4f).roundToInt()
        val fatGoal = (caloriesGoal * userInfo.fatRatio / 9f).roundToInt()

        return Result(
            carbsGoal = carbsGoal,
            proteinGoal = proteinGoal,
            fatGoal = fatGoal,
            caloriesGoal = caloriesGoal,
            totalCaloriesAte = totalCaloriesAte,
            totalCarbsAte = totalCarbsAte,
            totalFatAte = totalFatAte,
            totalProteinAte = totalProteinAte,
            mealNutrients = allNutrients
        )
    }


    private fun bmr(userInfo: UserInfo): Int {
        return when(userInfo.gender) {
            is Gender.Male -> {
                (66.47f + 13.75f * userInfo.weight +
                        5f * userInfo.height - 6.75f * userInfo.age).roundToInt()
            }
            is Gender.Female ->  {
                (665.09f + 9.56f * userInfo.weight +
                        1.84f * userInfo.height - 4.67 * userInfo.age).roundToInt()
            }
        }
    }

    private fun dailyCaloryRequirement(userInfo: UserInfo): Int {
        val activityFactor = when(userInfo.activityLevel) {
            is ActivityLevel.Low -> 1.2f
            is ActivityLevel.Medium -> 1.3f
            is ActivityLevel.High -> 1.4f
        }
        val caloryExtra = when(userInfo.goalType) {
            is GoalType.LoseWeight -> -500
            is GoalType.KeepWeight -> 0
            is GoalType.GainWeight -> 500
        }
        return (bmr(userInfo) * activityFactor + caloryExtra).roundToInt()
    }


    data class MealNutrients(
        val carbs:Int,
        val protein:Int,
        val fat:Int,
        val calories:Int,
        val mealType:MealType,
    )

    data class Result(
        val carbsGoal:Int,
        val proteinGoal:Int,
        val fatGoal:Int,
        val caloriesGoal:Int,
        val totalCaloriesAte:Int,
        val totalProteinAte:Int,
        val totalFatAte:Int,
        val totalCarbsAte:Int,
        val mealNutrients:Map<MealType,MealNutrients>,
    )

}