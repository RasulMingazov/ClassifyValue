package com.jeanbernad.classifyvalue.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.jeanbernad.classifyvalue.core.featureApi.FeatureApi

interface AppFeatures {

    @Composable
    fun Bind(modifier: Modifier, navController: NavHostController)

    data class Features(private val features: List<FeatureApi>) : AppFeatures {

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

    object Preview : AppFeatures {

        @Composable
        override fun Bind(modifier: Modifier, navController: NavHostController) = Unit
    }
}