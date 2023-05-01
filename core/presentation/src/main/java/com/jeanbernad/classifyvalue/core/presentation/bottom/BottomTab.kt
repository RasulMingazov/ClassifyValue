package com.jeanbernad.classifyvalue.core.presentation.bottom

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.jeanbernad.classifyvalue.theme.AppTheme

interface BottomTab {

    @Composable
    fun Bind(
        scope: RowScope,
        currentRoute: String,
        navController: NavController
    )

    fun route(): String

    abstract class Base(private val title: Int, private val icon: Int, private val route: String) :
        BottomTab {

        override fun route() = route

        @Composable
        override fun Bind(
            scope: RowScope,
            currentRoute: String,
            navController: NavController
        ) {
            scope.BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = stringResource(id = title)
                    )
                },
                label = {
                    Text(
                        text = stringResource(title),
                        style = AppTheme.typography().text12Bold()
                    )
                },
                selected = currentRoute == route,
                selectedContentColor = AppTheme.colors().textColor(),
                unselectedContentColor = AppTheme.colors().textColor().copy(0.3f),
                onClick = {
                    if (currentRoute != route)
                        navController.navigate(route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                },
                modifier = Modifier.navigationBarsPadding()
            )
        }
    }
}