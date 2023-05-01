package com.jeanbernad.classifyvalue

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.jeanbernad.classifyvalue.core.presentation.BaseActivity
import com.jeanbernad.classifyvalue.core.presentation.bottom.BottomTabs
import com.jeanbernad.classifyvalue.navigation.AppFeatures
import com.jeanbernad.classifyvalue.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override val viewModel by viewModels<MainViewModel.Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val appNavigationFeatures = viewModel.navigationFeatures().collectAsState()
                val bottomTabs = viewModel.bottomTabs().collectAsState()
                Inner(
                    appFeatures = appNavigationFeatures.value,
                    bottomTabs = bottomTabs.value
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Preview() {
        Inner(
            appFeatures = AppFeatures.Preview,
            bottomTabs = BottomTabs.Preview
        )
    }

    @Composable
    fun Inner(
        appFeatures: AppFeatures,
        bottomTabs: BottomTabs
    ) {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = { bottomTabs.Bind(navController = navController) },
            content = {
                Box(
                    modifier = Modifier
                        .padding(it)
                        .background(
                            alpha = 0.06f,
                            brush = AppTheme
                                .gradients()
                                .lightGreen()
                        )
                ) {
                    appFeatures.Bind(
                        modifier = Modifier,
                        navController = navController
                    )
                }
            }
        )
    }
}