package com.jeanbernad.classifyvalue.core.presentation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable

abstract class BaseActivity : ComponentActivity() {

    protected abstract val viewModel: BaseViewModel

}