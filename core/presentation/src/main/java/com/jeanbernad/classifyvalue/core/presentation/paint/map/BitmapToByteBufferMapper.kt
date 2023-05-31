package com.jeanbernad.classifyvalue.core.presentation.paint.map

import android.graphics.Bitmap
import com.jeanbernad.classifyvalue.core.Mapper
import java.nio.ByteBuffer
import java.nio.ByteOrder

class BitmapToByteBufferMapper : Mapper<Bitmap, ByteBuffer> {

    override fun map(item: Bitmap): ByteBuffer {
        val scaledImage = Bitmap.createScaledBitmap(item, SIZE, SIZE, true)

        val byteBuffer =
            ByteBuffer.allocateDirect(MODEL_INPUT_SIZE).apply { order(ByteOrder.nativeOrder()) }

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
            val alpha = pixelValue shr 24 and 0xFF

            val r = pixelValue shr 16 and 0xFF
            val g = pixelValue shr 8 and 0xFF
            val b = pixelValue and 0xFF

            val normalized = (r + g + b) / 3.0f

            val grayScaled = normalized / 255.0f
            val withAlpha = grayScaled * (alpha / 255.0f)

            byteBuffer.putFloat(1 - withAlpha)
        }

        return byteBuffer
    }

    companion object {
        private const val SIZE = 28
        private const val MODEL_INPUT_SIZE = 4 * SIZE * SIZE
    }
}