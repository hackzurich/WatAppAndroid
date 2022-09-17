package com.hackzurich.hackzurich22

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hackzurich.hackzurich22.navigation.AppNavigation
import com.hackzurich.hackzurich22.navigation.Screen
import com.hackzurich.hackzurich22.ui.theme.HackZurich22Theme

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun App() {
    val items = listOf(
        Screen.Home,
        Screen.WaterBank,
        Screen.Quiz,
    )

    var bottomBarVisible by remember { mutableStateOf(true) }
    val changeBottomBarVisibility: (Boolean) -> Unit = { bottomBarVisible = it }
    var openDialog by remember { mutableStateOf(false) }
    val changeOpenDialog: (Boolean) -> Unit = { openDialog = it }

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
                        //navController.navigate(Screen.Challenge.route)
                        //bottomBarVisible = false
                        openDialog = true
                    }
                }

                if (openDialog) {
                    QuizDialog(changeOpenDialog)

                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
private fun QuizDialog(changeOpenDialog: (Boolean) -> Unit) {
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = { changeOpenDialog(false) }
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
        ) {
            Image(
                painterResource(id = R.drawable.chocolate),
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
                modifier = Modifier
                    .weight(1f, fill = false)
                    .fillMaxWidth()
            )
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Did you know it takes 1700 litres to make a 100g chocolate bar? Most of the water is consumed by the cocoa tree that grows the cocoa beans, and many farms utilize the rain-heavy climates of rainforests to satisfy their thirst. But one third of all water consumption from the entire chocolate production process goes not to the trees but towards transportation and storage of the cocoa."
            )
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                TextButton(onClick = { changeOpenDialog(false) }) {
                    Text("Close")
                }
                Button(onClick = {}) {
                    Text("What can I do?")
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
                icon = {
                    Icon(
                        painterResource(id = screen.iconId ?: R.drawable.logo),
                        contentDescription = null
                    )
                },
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