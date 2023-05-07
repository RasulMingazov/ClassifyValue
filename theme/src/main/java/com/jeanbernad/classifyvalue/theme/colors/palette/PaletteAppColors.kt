package com.jeanbernad.classifyvalue.theme.colors.palette

import com.jeanbernad.classifyvalue.theme.colors.local.LocalAppColors
import com.jeanbernad.classifyvalue.theme.colors.theme.AppThemeColors

class PaletteAppColors : PaletteColors {

    private val localColors = LocalAppColors()

    override fun lightMode() = AppThemeColors(
        primary = localColors.pink(),
        secondary = localColors.lime(),
        higher = localColors.darkPurple(),
        firstBackground = localColors.lightLime(),
        secondBackground = localColors.lightPink(),
        thirdBackground = localColors.partyPink(),
        textColor = localColors.black(),
        secondTextColor = localColors.white(),
        bottom = localColors.bottomPink(),
        border = localColors.grayPurple(),
        firstMonochrome = localColors.white(),
        secondMonochrome = localColors.black()
    )

    override fun darkMode() = AppThemeColors(
        primary = localColors.pink(),
        secondary = localColors.lime(),
        higher = localColors.darkPurple(),
        firstBackground = localColors.lightLime(),
        secondBackground = localColors.lightPink(),
        thirdBackground = localColors.partyPink(),
        textColor = localColors.white(),
        secondTextColor = localColors.black(),
        bottom = localColors.bottomPink(),
        border = localColors.grayPurple(),
        firstMonochrome = localColors.black(),
        secondMonochrome = localColors.white()
    )
}