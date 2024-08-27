package com.farzin.core.util

import android.content.Context

sealed class UIText {

    data class DynamicString(val value: String) : UIText()
    data class StringResource(val resId:Int) : UIText()


    fun asString(context: Context)  =
        when(this){
            is DynamicString -> value
            is StringResource -> context.getString(resId)
        }
}