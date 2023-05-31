package com.jeanbernad.classifyvalue.feature.digit.data

import android.content.Context
import android.content.res.AssetManager
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.channels.FileChannel

interface RecognizeDigit {

    fun recognize(buffer: ByteBuffer): Pair<Int, Float>

    class Base(private val context: Context): RecognizeDigit {

        private fun loadModelFile(assetManager: AssetManager, fileName: String): ByteBuffer {
            val fileDescriptor = assetManager.openFd(fileName)
            val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
            val fileChannel = inputStream.channel
            val startOffset = fileDescriptor.startOffset
            val declaredLength = fileDescriptor.declaredLength
            return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
        }

        override fun recognize(buffer: ByteBuffer): Pair<Int, Float> {
            val assetManager = context.assets
            val model = loadModelFile(assetManager, "digit_recognition_model.tflite")
            val options = Interpreter.Options()
            options.setUseNNAPI(true)
            val interpreter = Interpreter(model, options)

            val output = Array(1) { FloatArray(OUTPUT_CLASS) }

            interpreter.run(buffer, output)

            val result = output[0]

            val maxindex = result.indices.maxBy { result[it] } ?: -1

            return maxindex to result[maxindex]
        }
    }

    companion object {
        private const val PIXEL_VALUE = 1
        private const val FLOAT_TYPE = 4
        private const val OUTPUT_CLASS = 10

    }
}