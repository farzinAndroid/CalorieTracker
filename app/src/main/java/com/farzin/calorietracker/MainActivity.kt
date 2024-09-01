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
import com.farzin.calorietracker.ui.theme.CalorieTrackerTheme
import com.farzin.calorietracker.navigation.Route
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
                                WelcomeScreen(onNextClicked = {
                                    navController.navigate(Route.GENDER)
                                }
                                )
                            }

                            composable(Route.AGE) {
                                AgeScreen(
                                    snackBarHost =snackBarHostState,
                                    onNextClicked = {
                                        navController.navigate(Route.HEIGHT)
                                    }
                                )
                            }

                            composable(Route.GENDER) {
                                GenderScreen(onNextClicked = {
                                    navController.navigate(Route.AGE)
                                })
                            }

                            composable(Route.HEIGHT) {
                                HeightScreen(
                                    snackBarHost = snackBarHostState,
                                    onNextClicked = {
                                        navController.navigate(Route.WEIGHT)
                                    }
                                )
                            }

                            composable(Route.WEIGHT) {
                                WeightScreen(
                                    snackBarHost = snackBarHostState,
                                    onNextClicked = {
                                        navController.navigate(Route.ACTIVITY)
                                    }
                                )
                            }

                            composable(Route.NUTRIENT_GOAL) {
                                NutrientGoalScreen(snackBarHost = snackBarHostState, onNextClicked = {
                                    navController.navigate(Route.TRACKER_OVERVIEW)
                                })
                            }

                            composable(Route.ACTIVITY) {
                                ActivityLevelScreen(onNextClicked = {
                                    navController.navigate(Route.GOAL)
                                })
                            }

                            composable(Route.GOAL) {
                                GoalTypeScreen(onNextClicked = {
                                    navController.navigate(Route.NUTRIENT_GOAL)
                                })
                            }

                            composable(Route.TRACKER_OVERVIEW) {
                                TrackerOverviewScreen(onNavigateToSearch = {mealName,dayOfMonth,month,year ->
                                    navController.navigate(
                                        Route.SEARCH + "/$mealName" +
                                                "/$dayOfMonth" +
                                                "/$month" +
                                                "/$year"
                                    )
                                })
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