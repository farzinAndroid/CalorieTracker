package com.farzin.core.util

sealed class UIEvent {

    data class Navigate(val route: String) : UIEvent()
    data object NavigateUp : UIEvent()

}