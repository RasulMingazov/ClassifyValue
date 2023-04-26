package com.jeanbernad.classifyvalue.theme.gradient

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import com.jeanbernad.classifyvalue.theme.AppTheme

class AppGradients: Gradients {

    @Composable
    override fun lightGreen() = Brush.verticalGradient(
        colors = listOf(
            AppTheme.colors().firstBackground(),
            AppTheme.colors().firstBackground(),
            AppTheme.colors().secondBackground(),
            AppTheme.colors().thirdBackground()
        )
    )
}