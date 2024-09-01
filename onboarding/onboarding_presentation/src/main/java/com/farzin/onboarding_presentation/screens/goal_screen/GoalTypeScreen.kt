package com.farzin.onboarding_presentation.screens.goal_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.farzin.core.R
import com.farzin.core.domain.model.GoalType
import com.farzin.core.util.UIEvent
import com.farzin.core_ui.DarkGreen
import com.farzin.core_ui.LocalSpacing
import com.farzin.onboarding_presentation.components.ActionButton
import com.farzin.onboarding_presentation.components.SelectableButton
import kotlinx.coroutines.flow.collectLatest

@Composable
fun GoalTypeScreen(
    onNextClicked: () -> Unit,
    goalViewmodel: GoalViewmodel = hiltViewModel(),
) {

    LaunchedEffect(true) {
        goalViewmodel.uiEvent.collectLatest {
            when (it) {
                is UIEvent.Success -> onNextClicked()
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
                text = stringResource(R.string.lose_keep_or_gain_weight),
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(LocalSpacing.current.medium))

            Row {
                SelectableButton(
                    text = stringResource(R.string.lose),
                    isSelected = goalViewmodel.selectedGoalType == GoalType.LoseWeight,
                    color = MaterialTheme.colorScheme.DarkGreen,
                    selectedTextColor = Color.White,
                    onClick = { goalViewmodel.onGoalTypeClicked(GoalType.LoseWeight) },
                    textStyle = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Normal)
                )

                Spacer(modifier = Modifier.width(LocalSpacing.current.medium))

                SelectableButton(
                    text = stringResource(R.string.keep),
                    isSelected = goalViewmodel.selectedGoalType == GoalType.KeepWeight,
                    color = MaterialTheme.colorScheme.DarkGreen,
                    selectedTextColor = Color.White,
                    onClick = { goalViewmodel.onGoalTypeClicked(GoalType.KeepWeight) },
                    textStyle = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Normal)
                )

                Spacer(modifier = Modifier.width(LocalSpacing.current.medium))

                SelectableButton(
                    text = stringResource(R.string.gain),
                    isSelected = goalViewmodel.selectedGoalType == GoalType.GainWeight,
                    color = MaterialTheme.colorScheme.DarkGreen,
                    selectedTextColor = Color.White,
                    onClick = { goalViewmodel.onGoalTypeClicked(GoalType.GainWeight) },
                    textStyle = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Normal)
                )
            }


        }

        ActionButton(
            text = stringResource(
                R.string.next
            ),
            onClick = goalViewmodel::onNextClicked,
            modifier = Modifier
                .align(Alignment.BottomEnd)
        )

    }

}