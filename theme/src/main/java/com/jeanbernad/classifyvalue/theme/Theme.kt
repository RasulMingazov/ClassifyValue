package com.jeanbernad.classifyvalue.theme

import androidx.compose.runtime.Composable
import com.jeanbernad.classifyvalue.theme.colors.theme.ThemeColors
import com.jeanbernad.classifyvalue.theme.dimension.DimensionSizes
import com.jeanbernad.classifyvalue.theme.gradient.Gradients
import com.jeanbernad.classifyvalue.theme.typography.TypographyTypes

interface Theme {

    @Composable
    fun colors(): ThemeColors

    @Composable
    fun typography(): TypographyTypes

    @Composable
    fun dimensions(): DimensionSizes

    @Composable
    fun gradients(): Gradients

}