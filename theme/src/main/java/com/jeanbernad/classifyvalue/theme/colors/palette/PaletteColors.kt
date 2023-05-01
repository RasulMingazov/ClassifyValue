package com.jeanbernad.classifyvalue.theme.colors.palette

import com.jeanbernad.classifyvalue.theme.colors.theme.ThemeColors

interface PaletteColors {

    fun darkMode(): ThemeColors

    fun lightMode(): ThemeColors
}