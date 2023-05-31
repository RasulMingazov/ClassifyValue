package com.jeanbernad.classifyvalue.feature.camera.presentation

import android.graphics.Bitmap
import android.graphics.Matrix
import com.jeanbernad.classifyvalue.core.Mapper
import javax.inject.Inject

class BitmapRotationMapper @Inject constructor(): Mapper<Bitmap, Bitmap> {

    override fun map(item: Bitmap): Bitmap {
        val matrix = Matrix().apply { postRotate(90f) }
        return Bitmap.createBitmap(item, 0, 0, item.width, item.height, matrix, true)
    }
}