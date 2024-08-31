package com.farzin.tracker_presentation.screens.tracker_overview_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.farzin.core.util.UIEvent
import com.farzin.core_ui.LocalSpacing

@Composable
fun TrackerOverviewScreen(
    onNavigate: (UIEvent.Navigate) -> Unit,
    viewmodel: TrackerOverviewViewmodel = hiltViewModel(),
) {

    val state = viewmodel.state
    val context = LocalContext.current


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = LocalSpacing.current.medium)
    ) {
        item {
            NutrientsHeader(state = state)
        }
    }

}