package com.farzin.onboarding_presentation.screens.height_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.core.domain.preferences.Preferences
import com.farzin.core.domain.use_case.FilterOutDigitsUseCase
import com.farzin.core.util.UIEvent
import com.farzin.core.util.UIText
import com.farzin.core.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeightViewmodel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigitsUseCase: FilterOutDigitsUseCase
) : ViewModel() {

    var height by mutableStateOf("180")
        private set


    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onHeightEnter(height:String){
        if (height.length <= 3){
            this.height = filterOutDigitsUseCase.invoke(height)
        }
    }

    fun onNextClicked(){
        viewModelScope.launch {
            val heightNum = height.toIntOrNull() ?: run {
                _uiEvent.send(
                    UIEvent.ShowSnackBar(UIText.StringResource(R.string.error_height_cant_be_empty))
                )
                return@launch
            }
            preferences.saveHeight(heightNum)
            _uiEvent.send(UIEvent.Success)
        }
    }

}