package com.hackzurich.hackzurich22

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WelcomeScreen() {
    Text("Welcome to HackZurich 2022")
}

@Preview
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreen()
}