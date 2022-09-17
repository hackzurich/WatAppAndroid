package com.hackzurich.hackzurich22

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.hackzurich.hackzurich22.ui.theme.HackZurich22Theme

@Composable
fun App() {
    HackZurich22Theme {
        val navController = rememberNavController()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            AppNavigation(navController = navController)
        }
    }
}