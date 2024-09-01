package com.farzin.tracker_presentation.screens.search_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.farzin.core.R
import com.farzin.core_ui.LightGray
import com.farzin.core_ui.LocalSpacing
import com.farzin.core_ui.TextWhite

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChanged: (String) -> Unit,
    onSearch: () -> Unit,
    hint: String = stringResource(R.string.search),
    shouldShowHint: Boolean = false,
    onFocusChanged: (FocusState) -> Unit,
) {

    val spacing = LocalSpacing.current

    Box(
        modifier = modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChanged,
            singleLine = true,
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch()
                    defaultKeyboardAction(ImeAction.Search)
                }
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .padding(2.dp)
                .shadow(2.dp, RoundedCornerShape(5.dp))
                .background(MaterialTheme.colorScheme.TextWhite)
                .fillMaxWidth()
                .padding(spacing.medium)
                .padding(end = spacing.medium)
                .onFocusChanged { onFocusChanged(it) }
        )

        if (shouldShowHint) {
            Text(
                text = hint,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.LightGray,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = spacing.medium)
            )
        }

        IconButton(
            onClick = onSearch,
            modifier = Modifier
                .align(Alignment.CenterEnd)
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "")
        }

    }


}