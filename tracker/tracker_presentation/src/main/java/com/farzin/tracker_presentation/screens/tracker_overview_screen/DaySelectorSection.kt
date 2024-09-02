package com.farzin.tracker_presentation.screens.tracker_overview_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.farzin.core_ui.TextBlack
import com.farzin.core.R
import java.time.LocalDate

@Composable
fun DaySelectorSection(
    date: LocalDate,
    onPrevDayClicked: () -> Unit,
    onNextDayClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {


    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = onPrevDayClicked) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.previous_day)
            )
        }


        Text(
            text = parseDateText(date = date),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.TextBlack
        )


        IconButton(onClick = onNextDayClicked) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = stringResource(R.string.next_day)
            )
        }

    }


}
