package com.jeanbernad.classifyvalue.core.presentation.button

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.jeanbernad.classifyvalue.theme.AppTheme

@Composable
fun RoundedButton(modifier: Modifier = Modifier, @StringRes text: Int, onClick: (() -> Unit)) {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = AppTheme.colors().higher(),
            contentColor = AppTheme.colors().secondTextColor()
        ),
        shape = RoundedCornerShape(AppTheme.dimensions().size12()),
        modifier = modifier,
        onClick = {
            onClick()
        },
        content = {
            Text(
                modifier = Modifier.padding(vertical = AppTheme.dimensions().size4()),
                text = stringResource(id = text),
                textAlign = TextAlign.Right,
                style = AppTheme.typography().text14(),
            )
        }
    )
}