package com.farzin.calorietracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.farzin.calorietracker.navigation.navigate
import com.farzin.calorietracker.ui.theme.CalorieTrackerTheme
import com.farzin.core.navigation.Route
import com.farzin.onboarding_presentation.welcome.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalorieTrackerTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Route.WELCOME
                ){
                    composable(Route.WELCOME){
                        WelcomeScreen(onNavigate = navController::navigate)
                    }

                    composable(Route.AGE){
                    }

                    composable(Route.GENDER){

                    }

                    composable(Route.HEIGHT){

                    }

                    composable(Route.NUTRIENT_GOAL){

                    }

                    composable(Route.ACTIVITY){

                    }

                    composable(Route.GOAL){

                    }

                    composable(Route.TRACKER_OVERVIEW){

                    }

                    composable(Route.SEARCH){

                    }
                }

            }
        }
    }
}