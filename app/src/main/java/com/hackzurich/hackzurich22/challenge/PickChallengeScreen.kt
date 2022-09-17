package com.hackzurich.hackzurich22.challenge

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val challenges =
    listOf(Icons.Default.Email, Icons.Default.Email, Icons.Default.Email, Icons.Default.Email)

@Composable
fun PickChallengeScreen() {
    Column {
        Text("Pick your Challenge")
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp)
        ) {
            items(challenges.count()) { index ->
                Box(
                    Modifier
                        .size(130.dp)
                        .padding(8.dp)
                ) {
                    Box(
                        Modifier
                            .background(Color.Cyan)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(challenges[index], contentDescription = null)
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun PickChallengeScreenPreview() {
    PickChallengeScreen()
}