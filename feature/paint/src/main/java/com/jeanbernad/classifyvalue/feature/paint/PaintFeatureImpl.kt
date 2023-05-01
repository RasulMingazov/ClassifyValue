package com.jeanbernad.classifyvalue.feature.paint

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.jeanbernad.classifyvalue.feature.paint.presentation.PaintDigitScreen
import com.jeanbernad.classifyvalue.feature.paint.presentation.PaintDigitViewModel
import com.jeanbernad.classifyvalue.feature.paintApi.PaintFeatureApi

class PaintFeatureImpl : PaintFeatureApi {

    override fun route() = ROUTE

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route()) { backStackEntry ->
            val parentEntry = remember(backStackEntry) { navController.getBackStackEntry(route()) }
            PaintDigitScreen(
                viewModel = hiltViewModel<PaintDigitViewModel.Item>(parentEntry),
                modifier = modifier,
                navController = navController
            )
        }
    }

    companion object {
        private const val ROUTE = "paint"
    }
}