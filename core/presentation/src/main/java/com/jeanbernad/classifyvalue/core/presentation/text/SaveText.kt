package com.jeanbernad.classifyvalue.core.presentation.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun SaveText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle
) {
    val resultText = rememberSaveable { mutableStateOf("") }
    if (text.isNotEmpty())
        resultText.value = text
    Text(
        modifier = modifier,
        text = resultText.value,
        style = style
    )
}