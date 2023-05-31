package com.jeanbernad.classifyvalue.core.presentation

import androidx.compose.runtime.saveable.Saver

interface SaveState<O, S: Any> {

    fun saver(): Saver<O, S>
}