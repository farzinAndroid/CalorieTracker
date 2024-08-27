package com.farzin.onboarding_presentation.screens.nutrient_goal_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.farzin.core.R
import com.farzin.core.util.UIEvent
import com.farzin.core_ui.LocalSpacing
import com.farzin.onboarding_presentation.components.ActionButton
import com.farzin.onboarding_presentation.components.UnitTextField
import kotlinx.coroutines.flow.collectLatest

@Composable
fun NutrientGoalScreen(
    nutrientGoalViewmodel: NutrientGoalViewmodel = hiltViewModel(),
    snackBarHost: SnackbarHostState,
    onNavigate: (UIEvent.Navigate) -> Unit,
) {

    val context = LocalContext.current

    LaunchedEffect(true) {
        nutrientGoalViewmodel.uiEvent.collectLatest {
            when (it) {
                is UIEvent.Navigate -> onNavigate(it)
                is UIEvent.ShowSnackBar -> {
                    snackBarHost.showSnackbar(
                        message = it.message.asString(context),
                    )
                }

                else -> Unit
            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalSpacing.current.large)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.what_are_your_nutrient_goals),
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(LocalSpacing.current.medium))

            UnitTextField(
                value = nutrientGoalViewmodel.state.carbRatio,
                onValueChanged = {
                    nutrientGoalViewmodel.onEvent(
                        NutrientGoalEvent.OnCarbRatioEnter(it)
                    )
                },
                unit = stringResource(R.string.percent_carbs)
            )

            Spacer(modifier = Modifier.height(LocalSpacing.current.medium))

            UnitTextField(
                value = nutrientGoalViewmodel.state.proteinRatio,
                onValueChanged = {
                    nutrientGoalViewmodel.onEvent(
                        NutrientGoalEvent.OnProteinRatioEnter(it)
                    )
                },
                unit = stringResource(R.string.percent_proteins)
            )

            Spacer(modifier = Modifier.height(LocalSpacing.current.medium))

            UnitTextField(
                value = nutrientGoalViewmodel.state.fatRatio,
                onValueChanged = {
                    nutrientGoalViewmodel.onEvent(
                        NutrientGoalEvent.OnFatRatioEnter(it)
                    )
                },
                unit = stringResource(R.string.percent_fats)
            )


        }

        ActionButton(
            text = stringResource(
                R.string.next
            ),
            onClick = {
                nutrientGoalViewmodel.onEvent(NutrientGoalEvent.OnNextClicked)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
        )

    }

}