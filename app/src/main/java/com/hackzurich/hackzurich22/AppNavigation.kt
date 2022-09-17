package com.hackzurich.hackzurich22

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hackzurich.hackzurich22.assessment.AssessmentScreen
import com.hackzurich.hackzurich22.challenge.ChallengeScreen

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Welcome : Screen("welcome", R.string.welcome)
    object Page1 : Screen("page1", R.string.page1)
    object Page2 : Screen("page2", R.string.page2)
    object Challenge: Screen("challenge", R.string.challenge)
    object Assessment: Screen("assessment", R.string.assessment)
}


@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    makeBottomBarVisible: (Boolean) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val shouldShowAssessment by viewModel.shouldShowAssessment.collectAsState(initial = false)

    LaunchedEffect(shouldShowAssessment) {
        if (shouldShowAssessment) {
            navController.navigate(Screen.Assessment.route)
            makeBottomBarVisible(false)
        }
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {
        composable(Screen.Welcome.route) {
            WelcomeScreen()
        }
        composable(Screen.Page1.route) {
            Page1Screen()
        }
        composable(Screen.Page2.route) {
            Page2Screen()
        }
        composable(Screen.Challenge.route) {
            ChallengeScreen {
                navController.popBackStack()
                makeBottomBarVisible(true)
            }
        }
        composable(Screen.Assessment.route) {
            AssessmentScreen {
                viewModel.didShowAssessment()
                navController.popBackStack()
                makeBottomBarVisible(true)
            }
        }
    }
}