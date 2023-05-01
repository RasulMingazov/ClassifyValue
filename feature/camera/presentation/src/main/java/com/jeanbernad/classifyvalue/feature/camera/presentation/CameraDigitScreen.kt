package com.jeanbernad.classifyvalue.feature.camera.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.jeanbernad.classifyvalue.theme.AppTheme

@Composable
fun CameraDigitScreen(
    viewModel: CameraDigitViewModel,
    modifier: Modifier,
    navController: NavController
) {
    Text(
        modifier = Modifier.padding(vertical = AppTheme.dimensions().size4()),
        text = stringResource(id = com.jeanbernad.classifyvalue.core.stringResources.R.string.camera),
        textAlign = TextAlign.Center,
        style = AppTheme.typography().text14(),
    )
}