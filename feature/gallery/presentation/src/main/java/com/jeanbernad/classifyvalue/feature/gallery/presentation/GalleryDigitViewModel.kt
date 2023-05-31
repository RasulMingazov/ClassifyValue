package com.jeanbernad.classifyvalue.feature.gallery.presentation

import android.graphics.Bitmap
import androidx.lifecycle.viewModelScope
import com.jeanbernad.classifyvalue.core.presentation.BaseViewModel
import com.jeanbernad.classifyvalue.feature.digit.domain.RecognizeDigitInteractor
import com.jeanbernad.classifyvalue.feature.paint.presentation.BitmapThresholdMapper
import com.jeanbernad.classifyvalue.feature.paint.presentation.BitmapToByteBuffer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

interface GalleryDigitViewModel {

    fun classify(bitmap: Bitmap)

    fun classified(): StateFlow<Pair<String, String>>

    fun resetClassified()

    abstract class Base(
        private val interactor: RecognizeDigitInteractor,
        private val bitmapToByteBuffer: BitmapToByteBuffer,
        private val bitmapThresholdMapper: BitmapThresholdMapper
    ): BaseViewModel(), GalleryDigitViewModel {

        private val _classified = MutableStateFlow(Pair("", ""))

        override fun classified(): StateFlow<Pair<String, String>> = _classified

        override fun classify(bitmap: Bitmap) {
            viewModelScope.launch {
                interactor.recognize(bitmapToByteBuffer.map(bitmapThresholdMapper.map(bitmap))).onEach {
                    _classified.emit(it)
                }.collect()
            }
        }

        override fun resetClassified() {
            _classified.value = Pair("", "")
        }
    }

    @HiltViewModel
    class Item @Inject constructor(
        interactor: RecognizeDigitInteractor,
        bitmapToByteBuffer: BitmapToByteBuffer,
        bitmapThresholdMapper: BitmapThresholdMapper
    ) : Base(interactor, bitmapToByteBuffer, bitmapThresholdMapper)
}