package com.jeanbernad.classifyvalue.feature.digit.data

import java.nio.ByteBuffer

interface RecognizeDigitSource {

    fun recognize(buffer: ByteBuffer): Pair<Int, Float>

    class Base(
        private val api: RecognizeDigit
    ): RecognizeDigitSource {

        override fun recognize(buffer: ByteBuffer): Pair<Int, Float> {
            return api.recognize(buffer)
        }
    }
}