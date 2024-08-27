package com.farzin.onboarding_presentation.weight_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.core.domain.preferences.Preferences
import com.farzin.core.domain.use_case.FilterOutDigits
import com.farzin.core.util.UIEvent
import com.farzin.core.util.UIText
import com.farzin.core.R
import com.farzin.core.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewmodel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits
) : ViewModel() {

    var weight by mutableStateOf("80.0")
        private set


    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onWeightEnter(weight:String){
        if (weight.length <= 4){
            this.weight = weight
        }
    }

    fun onNextClicked(){
        viewModelScope.launch {
            val weightNum = weight.toFloatOrNull() ?: run {
                _uiEvent.send(
                    UIEvent.ShowSnackBar(UIText.StringResource(R.string.error_weight_cant_be_empty))
                )
                return@launch
            }
            preferences.saveWeight(weightNum)
            _uiEvent.send(UIEvent.Navigate(Route.ACTIVITY))
        }
    }

}