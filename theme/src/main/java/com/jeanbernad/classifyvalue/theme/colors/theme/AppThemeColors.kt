package com.jeanbernad.classifyvalue.theme.colors.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

class AppThemeColors(
    private val primary: Color,
    private val secondary: Color,
    private val higher: Color,
    private val firstBackground: Color,
    private val secondBackground: Color,
    private val thirdBackground: Color,
    private val textColor: Color,
    private val secondTextColor: Color,
    private val bottom: Color,
    private val border: Color
) : ThemeColors {

    private var primaryLocal by mutableStateOf(primary)
    private var secondaryLocal by mutableStateOf(secondary)
    private var higherLocal by mutableStateOf(higher)
    private var firstBackgroundLocal by mutableStateOf(firstBackground)
    private var secondBackgroundLocal by mutableStateOf(secondBackground)
    private var thirdBackgroundLocal by mutableStateOf(thirdBackground)
    private var textColorLocal by mutableStateOf(textColor)
    private var secondTextColorLocal by mutableStateOf(secondTextColor)
    private var bottomLocal by mutableStateOf(bottom)
    private var borderLocal by mutableStateOf(border)

    override fun copy() = AppThemeColors(
        this.primary,
        this.secondary,
        this.higher,
        this.firstBackground,
        this.secondBackground,
        this.thirdBackground,
        this.textColor,
        this.secondTextColor,
        this.bottom,
        this.border
    )

    override fun update(other: ThemeColors) {
        primaryLocal = other.primary()
        secondaryLocal = other.secondary()
        higherLocal = other.higher()
        firstBackgroundLocal = other.firstBackground()
        secondBackgroundLocal = other.secondBackground()
        thirdBackgroundLocal = other.thirdBackground()
        textColorLocal = other.textColor()
        secondTextColorLocal = other.secondTextColor()
        bottomLocal = other.bottom()
        borderLocal = other.border()
    }


    override fun primary() = primaryLocal

    override fun secondary() = secondaryLocal

    override fun higher() = higherLocal

    override fun firstBackground() = firstBackgroundLocal

    override fun secondBackground() = secondBackgroundLocal

    override fun thirdBackground() = thirdBackgroundLocal

    override fun textColor() = textColorLocal

    override fun secondTextColor() = secondTextColorLocal

    override fun bottom() = bottomLocal

    override fun border() = borderLocal

}