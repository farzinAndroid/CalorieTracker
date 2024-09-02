package com.farzin.tracker_domain.use_case

import com.farzin.core.domain.model.ActivityLevel
import com.farzin.core.domain.model.Gender
import com.farzin.core.domain.model.GoalType
import com.farzin.core.domain.model.UserInfo
import com.farzin.core.domain.preferences.Preferences
import com.farzin.tracker_domain.model.MealType
import com.farzin.tracker_domain.model.TrackedFood
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import kotlin.random.Random

class CalculateMealNutrientsUseCaseTest {

    private lateinit var calculateMealNutrientsUseCase: CalculateMealNutrientsUseCase


    @Before
    fun setUp() {
        val preferences = mockk<Preferences>(relaxed = true)
        every { preferences.loadUserInfo() } returns
                UserInfo(
                    gender = Gender.Male,
                    age = 25,
                    weight = 80f,
                    height = 170,
                    carbRatio = 0.4f,
                    proteinRatio = 0.3f,
                    fatRatio = 0.3f,
                    activityLevel = ActivityLevel.Medium,
                    goalType = GoalType.KeepWeight,
                )

        calculateMealNutrientsUseCase = CalculateMealNutrientsUseCase(preferences)
    }

    @Test
    fun `Calories for breakfast properly calculated`() {
        val trackedFoods = (1..30).map {
            TrackedFood(
                amount = 100,
                mealType = MealType.fromString(
                    listOf("breakfast", "lunch", "dinner", "snack").random()
                ),
                calories = Random.nextInt(2000),
                fat = Random.nextInt(100),
                protein = Random.nextInt(100),
                carbs = Random.nextInt(100),
                date = LocalDate.now(),
                name = "name",
                imageUrl = null
            )
        }

        val result = calculateMealNutrientsUseCase.invoke(trackedFoods)

        val breakFastCalories =
            result.mealNutrients.values
                .filter { it.mealType == MealType.BreakFast }
                .sumOf { it.calories }

        val expectedCalories =
            trackedFoods
                .filter { it.mealType == MealType.BreakFast }
                .sumOf { it.calories }

        assertThat(breakFastCalories).isEqualTo(expectedCalories)

    }

    @Test
    fun `fat for lunch properly calculated`() {
        val trackedFoods = (1..30).map {
            TrackedFood(
                amount = 100,
                mealType = MealType.fromString(
                    listOf("breakfast", "lunch", "dinner", "snack").random()
                ),
                calories = Random.nextInt(2000),
                fat = Random.nextInt(100),
                protein = Random.nextInt(100),
                carbs = Random.nextInt(100),
                date = LocalDate.now(),
                name = "name",
                imageUrl = null
            )
        }

        val result = calculateMealNutrientsUseCase.invoke(trackedFoods)

        val lunchFats =
            result.mealNutrients.values
                .filter { it.mealType == MealType.Lunch }
                .sumOf { it.fat }

        val expectedfat =
            trackedFoods
                .filter { it.mealType == MealType.Lunch }
                .sumOf { it.fat }

        assertThat(lunchFats).isEqualTo(expectedfat)

    }

    @Test
    fun `protein for dinner properly calculated`() {
        val trackedFoods = (1..30).map {
            TrackedFood(
                amount = 100,
                mealType = MealType.fromString(
                    listOf("breakfast", "lunch", "dinner", "snack").random()
                ),
                calories = Random.nextInt(2000),
                fat = Random.nextInt(100),
                protein = Random.nextInt(100),
                carbs = Random.nextInt(100),
                date = LocalDate.now(),
                name = "name",
                imageUrl = null
            )
        }

        val result = calculateMealNutrientsUseCase.invoke(trackedFoods)

        val dinnerProtein =
            result.mealNutrients.values
                .filter { it.mealType == MealType.Dinner }
                .sumOf { it.protein }

        val expectedProtein =
            trackedFoods
                .filter { it.mealType == MealType.Dinner }
                .sumOf { it.protein }

        assertThat(dinnerProtein).isEqualTo(expectedProtein)

    }

}