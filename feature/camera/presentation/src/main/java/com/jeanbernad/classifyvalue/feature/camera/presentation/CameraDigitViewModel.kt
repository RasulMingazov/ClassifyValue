package com.jeanbernad.classifyvalue.feature.camera.presentation

import com.jeanbernad.classifyvalue.core.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface CameraDigitViewModel {

    abstract class Base: BaseViewModel(), CameraDigitViewModel

    @HiltViewModel
    class Item @Inject constructor() : Base()
}