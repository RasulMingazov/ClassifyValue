package com.jeanbernad.classifyvalue.feature.digit.data

import com.jeanbernad.classifyvalue.feature.digit.domain.RecognizeDigitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.nio.ByteBuffer

class BaseRecognizeDigitRepository(
    private val source: RecognizeDigitSource
) : RecognizeDigitRepository {

    override fun recognize(buffer: ByteBuffer): Flow<Pair<String, String>> = flow {
        emit(source.recognize(buffer))
    }.map { it.first.toString() to it.second.toString() }
}