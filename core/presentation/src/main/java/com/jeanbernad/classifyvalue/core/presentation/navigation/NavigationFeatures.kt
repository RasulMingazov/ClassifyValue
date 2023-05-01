package com.jeanbernad.classifyvalue.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.jeanbernad.classifyvalue.core.featureApi.FeatureApi

interface NavigationFeatures {

    @Composable
    fun Bind(modifier: Modifier, navController: NavHostController)

    class Items(private val features: List<FeatureApi>) : NavigationFeatures {

        @Composable
        override fun Bind(modifier: Modifier, navController: NavHostController) {
            NavHost(navController, startDestination = features.first().route()) {
                for (feature in features) {
                    feature.registerGraph(
                        navGraphBuilder = this,
                        navController = navController,
                        modifier = modifier
                    )
                }
            }
        }
    }

    object Preview : NavigationFeatures {

        @Composable
        override fun Bind(modifier: Modifier, navController: NavHostController) = Unit
    }
}