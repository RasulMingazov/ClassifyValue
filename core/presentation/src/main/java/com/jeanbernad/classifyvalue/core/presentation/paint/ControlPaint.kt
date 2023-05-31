package com.jeanbernad.classifyvalue.core.presentation.paint

import android.graphics.Bitmap
import android.view.View
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import com.jeanbernad.classifyvalue.core.presentation.SaveState
import com.jeanbernad.classifyvalue.core.presentation.paint.map.BitmapToByteBufferMapper
import com.jeanbernad.classifyvalue.core.presentation.paint.map.ViewToBitmapMapper
import com.jeanbernad.classifyvalue.core.presentation.paint.pojo.PaintModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import java.nio.ByteBuffer

abstract class ControlPaint {

    protected var paintModel: State<PaintModel> = mutableStateOf(PaintModel())

    private val bitmapConfig = MutableSharedFlow<Bitmap.Config>(extraBufferCapacity = 1)
    private val viewToBitmapMapper = ViewToBitmapMapper.Base()
    private val bitmapToByteBuffer = BitmapToByteBufferMapper()
    private var captureByteBuffer: (ByteBuffer) -> Unit = {}
    private var captureBitmap: (Bitmap) -> Unit = {}

    @Composable
    fun DrawPaintBox(modifier: Modifier = Modifier) {
        val view: View = LocalView.current
        val composableBounds = remember { mutableStateOf(Rect(0f, 0f, 0f, 0f)) }

        LaunchedEffect(this@ControlPaint) {
            bitmapConfig
                .map { config -> viewToBitmapMapper.map(view, config, composableBounds.value).apply { captureBitmap(this) } }
                .map { image -> bitmapToByteBuffer.map(image).apply { captureByteBuffer(this) }}
                .catch {  }
                .launchIn(this)
        }

        Canvas(modifier = modifier
            .onGloballyPositioned { composableBounds.value = it.boundsInWindow() }
            .background(color = paintModel.value.backgroundColor())
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset -> paintModel.value.addLine(offset) }) { change, _ ->
                    paintModel.value.addPoint(change.position)
                }
            }) {
            paintModel.value.drawLines(this)
        }
    }

    fun updateBackgroundColor(color: Color) {
        paintModel.value.updateBackgroundColor(color)
    }

    fun updatePaintColor(color: Color) {
        paintModel.value.updatePaintColor(color)
    }

    fun updatePaintWidth(width: Dp) {
        paintModel.value.updatePaintWidth(width)
    }

    fun captureByteBuffer(
        config: Bitmap.Config = Bitmap.Config.ARGB_8888,
        onCaptured: (ByteBuffer) -> Unit
    ) {
        this.captureByteBuffer = onCaptured
        bitmapConfig.tryEmit(config)
    }

    fun captureBitmap(
        config: Bitmap.Config = Bitmap.Config.ARGB_8888,
        onCaptured: (Bitmap) -> Unit
    ) {
        this.captureBitmap = onCaptured
        bitmapConfig.tryEmit(config)
    }

    fun reset() {
        paintModel.value.reset()
    }

    class Item : ControlPaint() {
        object StateSave : SaveState<ControlPaint, PaintModel> {
            override fun saver() = Saver<ControlPaint, PaintModel>(
                save = { it.paintModel.value },
                restore = { Item().apply { paintModel = mutableStateOf(it) } }
            )
        }

    }
}

@Composable
fun rememberPaintController(): ControlPaint {
    val saver = ControlPaint.Item.StateSave
    return rememberSaveable(saver = saver.saver()) { ControlPaint.Item() }
}