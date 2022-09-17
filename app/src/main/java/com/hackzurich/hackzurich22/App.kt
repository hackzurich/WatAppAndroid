package com.hackzurich.hackzurich22

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hackzurich.hackzurich22.ui.theme.HackZurich22Theme

@Composable
fun App() {
    val items = listOf(
        Screen.Welcome,
        Screen.Page1,
        Screen.Page2,
    )

    var bottomBarVisible by remember { mutableStateOf(true) }
    val changeBottomBarVisibility: (Boolean) -> Unit = { bottomBarVisible = it}

    HackZurich22Theme {
        val navController = rememberNavController()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
        ) {
            Scaffold(
                bottomBar = {
                    if (bottomBarVisible) {
                        BottomNav(navController, items)
                    }
                }

            ) { innerPadding ->
                AppNavigation(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController,
                    makeBottomBarVisible = changeBottomBarVisibility,
                )

                val intent = LocalContext.current.findActivity()?.intent
                LaunchedEffect(intent) {
                    if (intent != null && intent.hasExtra("pushtype")) {
                        navController.navigate(Screen.Challenge.route)
                        bottomBarVisible = false
                    }
                }
            }
        }
    }
}

@Composable
private fun BottomNav(
    navController: NavHostController,
    items: List<Screen>
) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                label = { Text(stringResource(screen.resourceId)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

private fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}