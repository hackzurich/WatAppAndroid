package com.hackzurich.hackzurich22.challenge

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

val challenges =
    listOf(Icons.Default.Email, Icons.Default.Email, Icons.Default.Email, Icons.Default.Email)

@ExperimentalMaterialApi
@Composable
fun PickChallengeScreen(makeBottomBarVisible: (Boolean) -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(modalBottomSheetState.isVisible) {
        makeBottomBarVisible(!modalBottomSheetState.isVisible)
    }

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetContent = {
            Box(modifier = Modifier
                .height(200.dp)
                .padding(8.dp)) {
                Text("This is a bottom sheet")
            }
        },
        sheetShape = RoundedCornerShape(8.dp, 8.dp, 0.dp, 0.dp)
    ) {
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
                                .fillMaxSize()
                                .clickable {
                                    coroutineScope.launch {
                                        makeBottomBarVisible(false)
                                        modalBottomSheetState.show()
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(challenges[index], contentDescription = null)
                        }
                    }
                }
            }

        }

    }

}

@ExperimentalMaterialApi
@Preview
@Composable
fun PickChallengeScreenPreview() {
    PickChallengeScreen {}
}