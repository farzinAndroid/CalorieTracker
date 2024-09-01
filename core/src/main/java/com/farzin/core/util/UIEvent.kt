package com.farzin.core.util

sealed class UIEvent {

    data object Success : UIEvent()
    data object NavigateUp : UIEvent()
    data class ShowSnackBar(val message:UIText) : UIEvent()

}