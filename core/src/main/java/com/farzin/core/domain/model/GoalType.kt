package com.farzin.core.domain.model

sealed class GoalType(val name: String) {

    data object LoseWeight : GoalType("lose_weight")
    data object GainWeight : GoalType("gain_weight")
    data object KeepWeight : GoalType("keep_weight")


    companion object {
        fun fromString(name: String): GoalType {
            return when (name) {
                "lose_weight" -> LoseWeight
                "gain_weight" -> GainWeight
                "keep_weight" -> KeepWeight
                else->KeepWeight
            }
        }
    }
}