package com.jeanbernad.classifyvalue.feature.paint.presentation

import android.graphics.Bitmap
import android.graphics.Color
import com.jeanbernad.classifyvalue.core.Mapper
import java.nio.ByteBuffer
import java.nio.ByteOrder
import javax.inject.Inject

class BitmapToByteBuffer @Inject constructor() : Mapper<Bitmap, ByteBuffer> {

    override fun map(item: Bitmap): ByteBuffer {
        val byteBuffer = ByteBuffer.allocateDirect(MODEL_INPUT_SIZE)
        byteBuffer.order(ByteOrder.nativeOrder())
        val scaledImage = Bitmap.createScaledBitmap(item, SIZE, SIZE, true)

        val pixels = IntArray(SIZE * SIZE)
        scaledImage.getPixels(
            pixels,
            0,
            scaledImage.width,
            0,
            0,
            scaledImage.width,
            scaledImage.height
        )
        for (pixelValue in pixels) {
            val alpha = Color.alpha(pixelValue)
            val r = Color.red(pixelValue)
            val g = Color.green(pixelValue)
            val b =  Color.blue(pixelValue)

            val normalized = (r + g + b) / 3.0f

            val grayScaled = normalized / 255.0f
            val withAlpha = grayScaled * (alpha / 255.0f)

            byteBuffer.putFloat(1 - withAlpha)
        }
        return byteBuffer
    }
    companion object {
        private const val PIXEL_VALUE = 1
        private const val FLOAT_TYPE = 4
        private const val SIZE = 28
        private const val MODEL_INPUT_SIZE = PIXEL_VALUE * FLOAT_TYPE * SIZE * SIZE
    }
}