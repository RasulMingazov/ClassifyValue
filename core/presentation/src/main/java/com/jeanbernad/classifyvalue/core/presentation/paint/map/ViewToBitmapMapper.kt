package com.jeanbernad.classifyvalue.core.presentation.paint.map

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Bitmap.Config
import android.os.Handler
import android.os.Looper
import android.view.PixelCopy
import android.view.View
import androidx.compose.ui.geometry.Rect
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface ViewToBitmapMapper {

    suspend fun map(view: View, config: Config, bounds: Rect): Bitmap

    class Base : ViewToBitmapMapper {

        override suspend fun map(view: View, config: Config, bounds: Rect): Bitmap =
            suspendCoroutine { continuation ->
                val window = (view.context as? Activity)?.window ?: throw IllegalStateException()

                Bitmap.createBitmap(view.width, view.height, config).apply {
                    PixelCopy.request(
                        window,
                        android.graphics.Rect(
                            bounds.left.toInt(),
                            bounds.top.toInt(),
                            bounds.right.toInt(),
                            bounds.bottom.toInt()
                        ),
                        this,
                        { copyResult ->
                            if (copyResult == PixelCopy.SUCCESS) continuation.resume(this)
                        },
                        Handler(Looper.getMainLooper())
                    )
                }
            }
    }
}