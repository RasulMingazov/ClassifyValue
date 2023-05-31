package com.jeanbernad.classifyvalue.feature.gallery

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.jeanbernad.classifyvalue.feature.gallery.presentation.GalleryDigitScreen
import com.jeanbernad.classifyvalue.feature.gallery.presentation.GalleryDigitViewModel
import com.jeanbernad.classifyvalue.feature.galleryApi.GalleryFeatureApi
import javax.inject.Inject

class GalleryFeatureImpl @Inject constructor(): GalleryFeatureApi {

    override fun route() = ROUTE

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(route()) { backStackEntry ->
            val parentEntry = remember(backStackEntry) { navController.getBackStackEntry(route()) }
            GalleryDigitScreen(
                viewModel = hiltViewModel<GalleryDigitViewModel.Item>(parentEntry),
                modifier = modifier,
                navController = navController
            )
        }
    }

    companion object {
        private const val ROUTE = "gallery"
    }
}