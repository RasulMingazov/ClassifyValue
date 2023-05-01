package com.jeanbernad.classifyvalue.core.presentation

import androidx.activity.ComponentActivity

abstract class BaseActivity : ComponentActivity() {

    protected abstract val viewModel: BaseViewModel

}