package com.jeanbernad.classifyvalue.core.presentation.bottom

import androidx.compose.material.BottomNavigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jeanbernad.classifyvalue.core.presentation.R
import com.jeanbernad.classifyvalue.core.stringResources.R as strResR
import com.jeanbernad.classifyvalue.theme.AppTheme

interface BottomTabs {

    @Composable
    fun Bind(navController: NavController)

    abstract class Base(private val tabs: List<BottomTab>) : BottomTabs {
        @Composable
        override fun Bind(navController: NavController) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route ?: tabs.first().route()
            val routes = remember { tabs.map { it.route() } }
            if (currentRoute in routes) {
                BottomNavigation(
                    backgroundColor = AppTheme.colors().bottom(),
                    contentColor = AppTheme.colors().textColor()

                ) {
                    tabs.forEach { tab ->
                        tab.Bind(
                            scope = this,
                            currentRoute = currentRoute,
                            navController = navController
                        )
                    }
                }
            }
        }
    }


    class Items(bottomTabs: List<BottomTab>) : Base(tabs = bottomTabs)

    object Preview : Base(
        tabs = listOf(
            FirstBottomTabExample(),
            SecondBottomTabExample(),
            ThirdBottomTabExample()
        )
    ) {

        internal class FirstBottomTabExample :
            BottomTab.Base(strResR.string.camera, R.drawable.ic_circle, "")

        internal class SecondBottomTabExample :
            BottomTab.Base(strResR.string.paint, R.drawable.ic_triangle, "")

        internal class ThirdBottomTabExample :
            BottomTab.Base(strResR.string.camera, R.drawable.ic_circle, "")
    }

}