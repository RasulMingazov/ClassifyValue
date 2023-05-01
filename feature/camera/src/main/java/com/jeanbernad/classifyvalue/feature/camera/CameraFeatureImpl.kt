package com.jeanbernad.classifyvalue.feature.camera

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.jeanbernad.classifyvalue.feature.camera.presentation.CameraDigitScreen
import com.jeanbernad.classifyvalue.feature.camera.presentation.CameraDigitViewModel
import com.jeanbernad.classifyvalue.feature.cameraApi.CameraFeatureApi
import javax.inject.Inject

class CameraFeatureImpl @Inject constructor(): CameraFeatureApi {

    override fun route() = ROUTE

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route()) { backStackEntry ->
            val parentEntry = remember(backStackEntry) { navController.getBackStackEntry(route()) }
            CameraDigitScreen(
                viewModel = hiltViewModel<CameraDigitViewModel.Item>(parentEntry),
                modifier = modifier,
                navController = navController
            )
        }
    }

    companion object {
        private const val ROUTE = "camera"
    }
}