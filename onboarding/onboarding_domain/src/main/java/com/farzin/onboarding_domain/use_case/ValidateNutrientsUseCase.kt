package com.farzin.onboarding_domain.use_case

import com.farzin.core.R
import com.farzin.core.util.UIText

class ValidateNutrientsUseCase {

    operator fun invoke(
        carbsRatio: String,
        proteinRatio: String,
        fatRatio: String,
    ): Result {

        val carbs = carbsRatio.toIntOrNull()
        val protein = proteinRatio.toIntOrNull()
        val fat = fatRatio.toIntOrNull()

        if (carbs == null || fat == null || protein == null) {
            return Result.Error(UIText.StringResource(R.string.error_invalid_values))
        }

        if (carbs + protein + fat != 100) {
            return Result.Error(UIText.StringResource(R.string.error_not_100_percent))
        }

        return Result.Success(carbs / 100f, protein / 100f, fat / 100f)
    }

    sealed class Result {
        data class Success(
            val carbsRatio: Float,
            val proteinRatio: Float,
            val fatRatio: Float,
        ) : Result()

        data class Error(val message: UIText) : Result()
    }
}