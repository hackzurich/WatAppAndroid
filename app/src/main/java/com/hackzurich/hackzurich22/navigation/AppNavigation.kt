package com.hackzurich.hackzurich22.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.hackzurich.hackzurich22.HomeScreen
import com.hackzurich.hackzurich22.LocationPermissionScreen
import com.hackzurich.hackzurich22.Page2Screen
import com.hackzurich.hackzurich22.R
import com.hackzurich.hackzurich22.assessment.AssessmentScreen
import com.hackzurich.hackzurich22.base.MainViewModel
import com.hackzurich.hackzurich22.challenge.ChallengeResultScreen
import com.hackzurich.hackzurich22.challenge.ChallengeScreen
import com.hackzurich.hackzurich22.challenge.PickChallengeScreen

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val iconId: Int? = null
) {
    object Home : Screen("home", R.string.home, R.drawable.ic_home)
    object Quiz : Screen("quiz", R.string.pick_challenge, R.drawable.ic_round_quiz)
    object WaterBank : Screen("waterbank", R.string.waterbank, R.drawable.ic_waterbank)
    object Challenge : Screen("challenge", R.string.challenge)
    object Assessment : Screen("assessment", R.string.assessment)
    object ChallengeResult : Screen("challenge_result", R.string.challenge_result)
    object LocationPermission : Screen("location_permission", R.string.location_permission)
}

@ExperimentalComposeUiApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalPermissionsApi
@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    makeBottomBarVisible: (Boolean) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val shouldShowAssessment by viewModel.shouldShowAssessment.collectAsState(initial = false)
    val shouldAskForLocationPermission by viewModel.shouldAskForLocationPermission.collectAsState(
        initial = false
    )

    LaunchedEffect(shouldShowAssessment, shouldAskForLocationPermission) {
        if (shouldShowAssessment) {
            navController.navigate(Screen.Assessment.route)
            makeBottomBarVisible(false)
        } else if (shouldAskForLocationPermission) {
            navController.navigate(Screen.LocationPermission.route)
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
            Page2Screen()
        }
        composable(Screen.WaterBank.route) {
            PickChallengeScreen()
        }
        composable(Screen.Challenge.route) {
            ChallengeScreen {
                navController.navigate(Screen.ChallengeResult.route)
            }
        }
        composable(Screen.Assessment.route) {
            AssessmentScreen {
                viewModel.didShowAssessment()
                navController.popBackStack()
                makeBottomBarVisible(true)
            }
        }
        composable(Screen.ChallengeResult.route) {
            ChallengeResultScreen {
                navController.popBackStack()
                navController.popBackStack()
                makeBottomBarVisible(true)
            }
        }
        composable(Screen.LocationPermission.route) {
            LocationPermissionScreen {
                viewModel.didAskLocationPermission()
                navController.popBackStack()
                makeBottomBarVisible(true)
            }
        }
    }
}