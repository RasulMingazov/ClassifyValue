package com.jeanbernad.classifyvalue.theme.resources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.jeanbernad.classifyvalue.theme.colors.theme.ThemeColors
import com.jeanbernad.classifyvalue.theme.dimension.DimensionSizes
import com.jeanbernad.classifyvalue.theme.gradient.Gradients
import com.jeanbernad.classifyvalue.theme.typography.TypographyTypes

interface Resources {

    @Composable
    @ReadOnlyComposable
    fun localColors(): ThemeColors

    @Composable
    @ReadOnlyComposable
    fun localTypography(): TypographyTypes

    @Composable
    @ReadOnlyComposable
    fun localDimensions(): DimensionSizes

    @Composable
    @ReadOnlyComposable
    fun localGradients(): Gradients

    @Composable
    fun LocalProvider(
        content: @Composable () -> Unit,
        colors: ThemeColors,
        typographyTypes: TypographyTypes,
        dimensionSizes: DimensionSizes,
        gradients: Gradients
    )
}