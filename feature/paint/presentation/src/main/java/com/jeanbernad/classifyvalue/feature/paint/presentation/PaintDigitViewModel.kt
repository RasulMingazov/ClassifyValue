package com.jeanbernad.classifyvalue.feature.paint.presentation

import com.jeanbernad.classifyvalue.core.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface PaintDigitViewModel {

    abstract class Base: BaseViewModel(), PaintDigitViewModel

    @HiltViewModel
    class Item @Inject constructor() : Base()
}