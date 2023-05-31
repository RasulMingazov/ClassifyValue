package com.jeanbernad.classifyvalue.feature.paint.presentation

import android.graphics.Bitmap
import android.graphics.Color
import com.jeanbernad.classifyvalue.core.Mapper
import java.nio.ByteBuffer
import java.nio.ByteOrder
import javax.inject.Inject

class BitmapThresholdMapper @Inject constructor() : Mapper<Bitmap, Bitmap> {

    override fun map(item: Bitmap): Bitmap {
        val thresholdBitmap = Bitmap.createBitmap(item.width, item.height, Bitmap.Config.ARGB_8888)

        for (y in 0 until item.height) {
            for (x in 0 until item.width) {
                val pixel = item.getPixel(x, y)
                val red = Color.red(pixel)
                val green = Color.green(pixel)
                val blue = Color.blue(pixel)
                val gray = (red + green + blue) / 3

                val thresholdPixel = if (gray >= MIDDLE) Color.WHITE else Color.BLACK
                thresholdBitmap.setPixel(x, y, thresholdPixel)
            }
        }
        return thresholdBitmap
    }

    companion object {
        private const val MIDDLE = 128
    }
}