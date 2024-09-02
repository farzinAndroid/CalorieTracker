package com.farzin.calorietracker

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.farzin.calorietracker.navigation.Route
import com.farzin.calorietracker.reporitory.TrackerRepositoryFake
import com.farzin.calorietracker.ui.theme.CalorieTrackerTheme
import com.farzin.core.domain.model.ActivityLevel
import com.farzin.core.domain.model.Gender
import com.farzin.core.domain.model.GoalType
import com.farzin.core.domain.model.UserInfo
import com.farzin.core.domain.preferences.Preferences
import com.farzin.core.domain.use_case.FilterOutDigitsUseCase
import com.farzin.tracker_domain.model.TrackableFood
import com.farzin.tracker_domain.use_case.CalculateMealNutrientsUseCase
import com.farzin.tracker_domain.use_case.DeleteTrackFoodUseCase
import com.farzin.tracker_domain.use_case.GetFoodForDateUseCase
import com.farzin.tracker_domain.use_case.InsertTrackFoodUseCase
import com.farzin.tracker_domain.use_case.SearchFoodUseCase
import com.farzin.tracker_domain.use_case.TrackerUseCasesWrapperClass
import com.farzin.tracker_presentation.screens.search_screen.SearchScreen
import com.farzin.tracker_presentation.screens.search_screen.SearchViewmodel
import com.farzin.tracker_presentation.screens.tracker_overview_screen.TrackerOverviewScreen
import com.farzin.tracker_presentation.screens.tracker_overview_screen.TrackerOverviewViewmodel
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.math.roundToInt


@HiltAndroidTest
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class TrackerOverviewE2E {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var fakeRepository: TrackerRepositoryFake
    private lateinit var preferences: Preferences
    private lateinit var trackerUseCasesWrapperClass: TrackerUseCasesWrapperClass
    private lateinit var trackerOverviewViewModel: TrackerOverviewViewmodel
    private lateinit var searchViewmodel: SearchViewmodel
    private lateinit var navController: NavHostController

    @Before
    fun setup() {
        preferences = mockk(relaxed = true)
        every { preferences.loadUserInfo() } returns UserInfo(
            gender = Gender.Male,
            age = 20,
            weight = 80f,
            height = 180,
            activityLevel = ActivityLevel.Medium,
            goalType = GoalType.KeepWeight,
            carbRatio = 0.4f,
            proteinRatio = 0.3f,
            fatRatio = 0.3f
        )
        fakeRepository = TrackerRepositoryFake()
        trackerUseCasesWrapperClass = TrackerUseCasesWrapperClass(
            insertTrackFoodUseCase = InsertTrackFoodUseCase(fakeRepository),
            deleteTrackFoodUseCase = DeleteTrackFoodUseCase(fakeRepository),
            getFoodForDateUseCase = GetFoodForDateUseCase(fakeRepository),
            calculateMealNutrientsUseCase = CalculateMealNutrientsUseCase(preferences),
            searchFoodUseCase = SearchFoodUseCase(fakeRepository)
        )
        trackerOverviewViewModel = TrackerOverviewViewmodel(
            preferences = preferences,
            trackerUseCasesWrapperClass = trackerUseCasesWrapperClass
        )
        searchViewmodel = SearchViewmodel(
            trackerUseCasesWrapperClass = trackerUseCasesWrapperClass,
            filterOutDigitsUseCase = FilterOutDigitsUseCase()
        )

        composeRule.setContent {
            CalorieTrackerTheme {
                navController = rememberNavController()
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
                            composable(Route.TRACKER_OVERVIEW) {
                                TrackerOverviewScreen(onNavigateToSearch = { mealName, dayOfMonth, month, year ->
                                    navController.navigate(
                                        Route.SEARCH + "/$mealName" +
                                                "/$dayOfMonth" +
                                                "/$month" +
                                                "/$year"
                                    )
                                },
                                    viewmodel = trackerOverviewViewModel
                                    )
                            }

                            composable(
                                Route.SEARCH + "/{mealName}/{dayOfMonth}/{month}/{year}",
                                arguments = listOf(
                                    navArgument("mealName") {
                                        type = NavType.StringType
                                    },
                                    navArgument("dayOfMonth") {
                                        type = NavType.IntType
                                    },
                                    navArgument("month") {
                                        type = NavType.IntType
                                    },
                                    navArgument("year") {
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
                                    mealName = mealName,
                                    dayOfMonth = dayOfMonth,
                                    month = month,
                                    year = year,
                                    onNavigateUp = { navController.navigateUp() },
                                    viewmodel = searchViewmodel
                                )
                            }
                        }
                    }
                )
            }

        }

    }

    @SuppressLint("CheckResult")
    @Test
    fun addBreakFast_appearsUnderBreakfast_NutrientsProperlyCalculated() {

        fakeRepository.searchResults = listOf(
            TrackableFood(
                name = "banana",
                imageUrl = null,
                caloriesPer100g = 150,
                carbsPer100g = 50,
                proteinsPer100g = 5,
                fatPer100g = 1
            )
        )

        val addedAmount = 150
        val expectedCalories = (1.5f * 150).roundToInt()
        val expectedCarbs = (1.5f * 50).roundToInt()
        val expectedProtein = (1.5f * 5).roundToInt()
        val expectedFat = (1.5f * 1).roundToInt()

        composeRule
            .onNodeWithText("Add Breakfast")
            .assertDoesNotExist()
        composeRule
            .onNodeWithContentDescription("Breakfast")
            .performClick()
        composeRule
            .onNodeWithText("Add Breakfast")
            .assertIsDisplayed()
        composeRule
            .onNodeWithText("Add Breakfast")
            .performClick()

        assertThat(
            navController
                .currentDestination
                ?.route
                ?.startsWith(Route.SEARCH)
        ).isTrue()

        composeRule
            .onNodeWithTag("search_textfield")
            .performTextInput("banana")
        composeRule
            .onNodeWithContentDescription("Search...")
            .performClick()
        composeRule
            .onNodeWithText("Carbs")
            .performClick()
        composeRule
            .onNodeWithContentDescription("Amount")
            .performTextInput(addedAmount.toString())
        composeRule
            .onNodeWithContentDescription("Track")
            .performClick()

        assertThat(
            navController
                .currentDestination
                ?.route
                ?.startsWith(Route.TRACKER_OVERVIEW)
        )

        composeRule
            .onAllNodesWithText(expectedCarbs.toStr())
            .onFirst()
            .assertIsDisplayed()
        composeRule
            .onAllNodesWithText(expectedProtein.toStr())
            .onFirst()
            .assertIsDisplayed()
        composeRule
            .onAllNodesWithText(expectedFat.toStr())
            .onFirst()
            .assertIsDisplayed()
        composeRule
            .onAllNodesWithText(expectedCalories.toStr())
            .onFirst()
            .assertIsDisplayed()

    }


}