package com.hackzurich.hackzurich22.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.ExperimentalMaterialApi
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
import com.hackzurich.hackzurich22.HomeScreen
import com.hackzurich.hackzurich22.Page2Screen
import com.hackzurich.hackzurich22.R
import com.hackzurich.hackzurich22.assessment.AssessmentScreen
import com.hackzurich.hackzurich22.base.MainViewModel
import com.hackzurich.hackzurich22.challenge.ChallengeScreen
import com.hackzurich.hackzurich22.challenge.PickChallengeScreen

sealed class Screen(val route: String, @StringRes val resourceId: Int, @DrawableRes val iconId: Int? = null) {
    object Home : Screen("home", R.string.home, R.drawable.ic_home)
    object Quiz : Screen("quiz", R.string.pick_challenge, R.drawable.ic_round_quiz)
    object WaterBank : Screen("waterbank", R.string.waterbank, R.drawable.ic_waterbank)
    object Challenge : Screen("challenge", R.string.challenge)
    object Assessment : Screen("assessment", R.string.assessment)
}

@ExperimentalMaterialApi
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
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen()
        }
        composable(Screen.Quiz.route) {
            PickChallengeScreen(
                makeBottomBarVisible = {
                    makeBottomBarVisible(it)
                }
            )
        }
        composable(Screen.WaterBank.route) {
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