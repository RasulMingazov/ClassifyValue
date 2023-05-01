package com.jeanbernad.classifyvalue.theme.colors.theme

import androidx.compose.ui.graphics.Color

interface ThemeColors {
    
    fun copy(): ThemeColors
    
    fun update(other: ThemeColors)

    fun primary(): Color

    fun secondary(): Color

    fun higher(): Color
    
    fun firstBackground(): Color
   
    fun secondBackground(): Color
  
    fun thirdBackground(): Color
  
    fun textColor(): Color
  
    fun secondTextColor(): Color

    fun bottom(): Color

    fun border(): Color
    
}