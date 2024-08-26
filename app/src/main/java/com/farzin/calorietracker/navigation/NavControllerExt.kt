package com.farzin.calorietracker.navigation

import androidx.navigation.NavController
import com.farzin.core.util.UIEvent


fun NavController.navigate(event: UIEvent.Navigate){
    this.navigate(event.route)
}