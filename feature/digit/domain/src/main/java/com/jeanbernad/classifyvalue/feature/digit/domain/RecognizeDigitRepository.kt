package com.jeanbernad.classifyvalue.feature.digit.domain

import kotlinx.coroutines.flow.Flow
import java.nio.ByteBuffer

interface RecognizeDigitRepository {

    fun recognize(buffer: ByteBuffer): Flow<Pair<String, String>>
}