package com.jeanbernad.classifyvalue.core.presentation.paint

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun PaintBox(
    modifier: Modifier = Modifier,
    controller: ControlPaint,
    background: Color = Color.White,
    paintColor: Color = Color.Black,
    paintWidth: Dp = 12.dp
) {
    controller.updatePaintWidth(paintWidth)
    controller.updateBackgroundColor(background)
    controller.updatePaintColor(paintColor)

    controller.DrawPaintBox(modifier)
}