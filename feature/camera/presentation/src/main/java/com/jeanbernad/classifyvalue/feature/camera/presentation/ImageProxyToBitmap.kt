package com.jeanbernad.classifyvalue.feature.camera.presentation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.camera.core.ImageProxy
import com.jeanbernad.classifyvalue.core.Mapper
import javax.inject.Inject

class ImageProxyToBitmap @Inject constructor() : Mapper<ImageProxy, Bitmap> {

    override fun map(item: ImageProxy): Bitmap {

        val buffer = item.planes[0].buffer
        val bytes = ByteArray(buffer.remaining())
        buffer.get(bytes)
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }
}