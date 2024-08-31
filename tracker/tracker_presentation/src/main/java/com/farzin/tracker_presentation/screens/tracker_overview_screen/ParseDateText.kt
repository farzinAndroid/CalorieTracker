package com.farzin.tracker_presentation.screens.tracker_overview_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.farzin.core.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun parseDateText(
    date: LocalDate
) : String {

    val today = LocalDate.now()

    return when(date){
        today-> stringResource(R.string.today)
        today.minusDays(1)->stringResource(R.string.yesterday)
        today.plusDays(1)->stringResource(R.string.tomorrow)
        else->{
            DateTimeFormatter.ofPattern("dd LLLL").format(date)
        }
    }
}