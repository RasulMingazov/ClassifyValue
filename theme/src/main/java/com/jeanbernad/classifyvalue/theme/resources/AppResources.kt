package com.jeanbernad.classifyvalue.theme.resources

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import com.jeanbernad.classifyvalue.theme.colors.palette.PaletteAppColors
import com.jeanbernad.classifyvalue.theme.colors.theme.ThemeColors
import com.jeanbernad.classifyvalue.theme.dimension.AppDimensionSizes
import com.jeanbernad.classifyvalue.theme.dimension.DimensionSizes
import com.jeanbernad.classifyvalue.theme.gradient.AppGradients
import com.jeanbernad.classifyvalue.theme.gradient.Gradients
import com.jeanbernad.classifyvalue.theme.typography.AppTypographyTypes
import com.jeanbernad.classifyvalue.theme.typography.TypographyTypes

object AppResources : Resources {

    private val LocalColors =
        staticCompositionLocalOf<ThemeColors> { PaletteAppColors().lightMode() }
    private val LocalTypography = staticCompositionLocalOf<TypographyTypes> { AppTypographyTypes() }
    private val LocalDimension = staticCompositionLocalOf<DimensionSizes> { AppDimensionSizes() }
    private val LocalGradients = staticCompositionLocalOf<Gradients> { AppGradients() }

    @Composable
    @ReadOnlyComposable
    override fun localColors() = LocalColors.current

    @Composable
    @ReadOnlyComposable
    override fun localTypography() = LocalTypography.current

    @Composable
    @ReadOnlyComposable
    override fun localDimensions() = LocalDimension.current

    @Composable
    @ReadOnlyComposable
    override fun localGradients() = LocalGradients.current

    @Composable
    override fun LocalProvider(content: @Composable () -> Unit, colors: ThemeColors, typographyTypes: TypographyTypes, dimensionSizes: DimensionSizes, gradients: Gradients) {
        val rememberedColors = remember { colors.copy() }.apply { update(colors) }
        CompositionLocalProvider(
            LocalColors provides rememberedColors,
            LocalDimension provides dimensionSizes,
            LocalTypography provides typographyTypes,
            LocalGradients provides gradients
        ) {
            content()
        }
    }
}