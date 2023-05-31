package com.jeanbernad.classifyvalue.feature.digit.domain

import java.nio.ByteBuffer
import javax.inject.Inject


class RecognizeDigitInteractor @Inject constructor(
    private val repository: RecognizeDigitRepository
) {

    fun recognize(buffer: ByteBuffer) = repository.recognize(buffer)
}
