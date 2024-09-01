package com.farzin.onboarding_presentation.screens.nutrient_goal_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.core.domain.preferences.Preferences
import com.farzin.core.domain.use_case.FilterOutDigitsUseCase
import com.farzin.core.util.UIEvent
import com.farzin.onboarding_domain.use_case.ValidateNutrientsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewmodel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigitsUseCase: FilterOutDigitsUseCase,
    private val validateNutrientsUseCase: ValidateNutrientsUseCase,
) : ViewModel() {


    var state by mutableStateOf(NutrientGoalState())
        private set


    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: NutrientGoalEvent){
        when(event){
            is NutrientGoalEvent.OnCarbRatioEnter -> {
                state = state.copy(carbRatio = filterOutDigitsUseCase.invoke(event.ratio))
            }
            is NutrientGoalEvent.OnProteinRatioEnter -> {
                state = state.copy(proteinRatio = filterOutDigitsUseCase.invoke(event.ratio))
            }
            is NutrientGoalEvent.OnFatRatioEnter -> {
                state = state.copy(fatRatio = filterOutDigitsUseCase.invoke(event.ratio))
            }
            NutrientGoalEvent.OnNextClicked -> {
                val result = validateNutrientsUseCase.invoke(
                    carbsRatio = state.carbRatio,
                    proteinRatio = state.proteinRatio,
                    fatRatio = state.fatRatio,
                )
                when(result){
                    is ValidateNutrientsUseCase.Result.Success -> {
                        preferences.saveProteinRatio(result.proteinRatio)
                        preferences.saveCarbRatio(result.carbsRatio)
                        preferences.saveFatRatio(result.fatRatio)
                        viewModelScope.launch {
                            _uiEvent.send(UIEvent.Success)
                        }
                    }
                    is ValidateNutrientsUseCase.Result.Error -> {
                        viewModelScope.launch {
                            _uiEvent.send(UIEvent.ShowSnackBar(result.message))
                        }
                    }
                }
            }
        }
    }

}