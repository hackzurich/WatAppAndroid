package com.hackzurich.hackzurich22.assessment

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun AssessmentScreen(chooseOption: () -> Unit) {
    Column {
        Text("Water saving habit assessment")
        Button(onClick = chooseOption) {
            Text("Choose an option")
        }
    }
}