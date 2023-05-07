package com.jeanbernad.classifyvalue.core.presentation.topbar

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.jeanbernad.classifyvalue.theme.AppTheme

@Composable
fun TopBar(@StringRes title: Int) {
    TopAppBar(
        backgroundColor = AppTheme.colors().primary(),
        content = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F, true),
                text = stringResource(id = title),
                textAlign = TextAlign.Center,
                color = AppTheme.colors().textColor(),
                style = AppTheme.typography().text16Bold()
            )
        }
    )
}