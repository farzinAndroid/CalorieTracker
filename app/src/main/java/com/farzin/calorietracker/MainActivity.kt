package com.farzin.calorietracker

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.farzin.calorietracker.navigation.navigate
import com.farzin.calorietracker.ui.theme.CalorieTrackerTheme
import com.farzin.core.navigation.Route
import com.farzin.onboarding_presentation.screens.activity_screen.ActivityLevelScreen
import com.farzin.onboarding_presentation.screens.age_screen.AgeScreen
import com.farzin.onboarding_presentation.screens.gender_screen.GenderScreen
import com.farzin.onboarding_presentation.screens.goal_screen.GoalTypeScreen
import com.farzin.onboarding_presentation.screens.height_screen.HeightScreen
import com.farzin.onboarding_presentation.screens.nutrient_goal_screen.NutrientGoalScreen
import com.farzin.onboarding_presentation.screens.weight_screen.WeightScreen
import com.farzin.onboarding_presentation.screens.welcome_screen.WelcomeScreen
import com.farzin.tracker_presentation.screens.search_screen.SearchScreen
import com.farzin.tracker_presentation.screens.tracker_overview_screen.TrackerOverviewScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalorieTrackerTheme {
                val navController = rememberNavController()
                val snackBarHostState = remember { SnackbarHostState() }
                Scaffold(
                    containerColor = Color.White,
                    snackbarHost = {
                        SnackbarHost(hostState = snackBarHostState)
                    },
                    content = { padding ->
                        NavHost(
                            navController = navController,
                            startDestination = Route.WELCOME
                        ) {
                            composable(Route.WELCOME) {
                                WelcomeScreen(onNavigate = navController::navigate)
                            }

                            composable(Route.AGE) {
                                AgeScreen(
                                    snackBarHost =snackBarHostState,
                                    onNavigate = navController::navigate
                                )
                            }

                            composable(Route.GENDER) {
                                GenderScreen(onNavigate = navController::navigate)
                            }

                            composable(Route.HEIGHT) {
                                HeightScreen(
                                    snackBarHost = snackBarHostState,
                                    onNavigate = navController::navigate
                                )
                            }

                            composable(Route.WEIGHT) {
                                WeightScreen(
                                    snackBarHost = snackBarHostState,
                                    onNavigate = navController::navigate
                                )
                            }

                            composable(Route.NUTRIENT_GOAL) {
                                NutrientGoalScreen(snackBarHost = snackBarHostState, onNavigate = navController::navigate)
                            }

                            composable(Route.ACTIVITY) {
                                ActivityLevelScreen(onNavigate = navController::navigate)
                            }

                            composable(Route.GOAL) {
                                GoalTypeScreen(onNavigate = navController::navigate)
                            }

                            composable(Route.TRACKER_OVERVIEW) {
                                TrackerOverviewScreen(onNavigate = navController::navigate)
                            }

                            composable(
                                Route.SEARCH + "/{mealName}/{dayOfMonth}/{month}/{year}",
                                arguments = listOf(
                                    navArgument("mealName"){
                                        type = NavType.StringType
                                    },
                                    navArgument("dayOfMonth"){
                                        type = NavType.IntType
                                    },
                                    navArgument("month"){
                                        type = NavType.IntType
                                    },
                                    navArgument("year"){
                                        type = NavType.IntType
                                    },
                                )
                            ) {
                                val mealName = it.arguments?.getString("mealName")!!
                                val dayOfMonth = it.arguments?.getInt("dayOfMonth")!!
                                val month = it.arguments?.getInt("month")!!
                                val year = it.arguments?.getInt("year")!!
                                SearchScreen(
                                    snackbarHostState = snackBarHostState,
                                    mealName =mealName,
                                    dayOfMonth =dayOfMonth,
                                    month =month,
                                    year =year,
                                    onNavigateUp = { navController.navigateUp() }
                                )
                            }
                        }
                    }
                )


            }
        }
    }
}