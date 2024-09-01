package com.farzin.onboarding_presentation.screens.gender_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.core.domain.model.Gender
import com.farzin.core.domain.preferences.Preferences
import com.farzin.core.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderViewmodel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    var selectedGender by mutableStateOf<Gender>(Gender.Male)
        private set


    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onGenderClicked(gender: Gender){
        selectedGender = gender
    }

    fun onNextClicked(){
        viewModelScope.launch {
            preferences.saveGender(selectedGender)
            _uiEvent.send(UIEvent.Success)
        }
    }

}