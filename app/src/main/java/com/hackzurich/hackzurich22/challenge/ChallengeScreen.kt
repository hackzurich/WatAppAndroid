package com.hackzurich.hackzurich22.challenge

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ChallengeScreen(answerChallenge: () -> Unit) {
    BackHandler {}

    Column() {
        Text("This will be the challenge screen")
        Button(onClick = answerChallenge) {
            Text("Answer the challenge")
        }
    }
}