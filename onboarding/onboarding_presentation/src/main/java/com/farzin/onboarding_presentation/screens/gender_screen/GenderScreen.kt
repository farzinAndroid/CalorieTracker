package com.farzin.onboarding_presentation.screens.gender_screen

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
import com.farzin.core.domain.model.Gender
import com.farzin.core.util.UIEvent
import com.farzin.core_ui.DarkGreen
import com.farzin.core_ui.LocalSpacing
import com.farzin.onboarding_presentation.components.ActionButton
import com.farzin.onboarding_presentation.components.SelectableButton
import kotlinx.coroutines.flow.collectLatest

@Composable
fun GenderScreen(
    onNavigate: (UIEvent.Navigate) -> Unit,
    genderViewmodel: GenderViewmodel = hiltViewModel(),
) {

    LaunchedEffect(true) {
        genderViewmodel.uiEvent.collectLatest {
            when (it) {
                is UIEvent.Navigate -> onNavigate(it)
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
                text = stringResource(R.string.whats_your_gender),
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(LocalSpacing.current.medium))

            Row {
                SelectableButton(
                    text = stringResource(R.string.male),
                    isSelected = genderViewmodel.selectedGender == Gender.Male,
                    color = MaterialTheme.colorScheme.DarkGreen,
                    selectedTextColor = Color.White,
                    onClick = { genderViewmodel.onGenderClicked(Gender.Male) },
                    textStyle = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Normal)
                )

                Spacer(modifier = Modifier.width(LocalSpacing.current.medium))

                SelectableButton(
                    text = stringResource(R.string.female),
                    isSelected = genderViewmodel.selectedGender == Gender.Female,
                    color = MaterialTheme.colorScheme.DarkGreen,
                    selectedTextColor = Color.White,
                    onClick = { genderViewmodel.onGenderClicked(Gender.Female) },
                    textStyle = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Normal)
                )
            }


        }

        ActionButton(
            text = stringResource(
                R.string.next
            ),
            onClick = genderViewmodel::onNextClicked,
            modifier = Modifier
                .align(Alignment.BottomEnd)
        )

    }

}