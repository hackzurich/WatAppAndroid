package com.hackzurich.hackzurich22.challenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackzurich.hackzurich22.R
import com.hackzurich.hackzurich22.components.Header
import com.hackzurich.hackzurich22.ui.theme.Primary
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun PickChallengeScreen(showDialog: () -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Header()
        Spacer(modifier = Modifier.height(16.dp))
        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(fontSize = 36.sp, fontWeight = FontWeight.Bold)) {
                append("What can I do\n")
            }
            withStyle(
                style = SpanStyle(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Primary
                )
            ) {
                append("today")
            }
            withStyle(style = SpanStyle(fontSize = 36.sp, fontWeight = FontWeight.Bold)) {
                append("?")
            }
        }, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp)
        ) {
            items(4) { index ->
                Box(
                    Modifier
                        .width(130.dp)
                        .height(200.dp)
                        .padding(1.dp)
                ) {
                    when (index) {
                        0 -> {
                            Box(
                                Modifier
                                    .background(Color(0x33377EA6))
                                    .fillMaxSize()
                                    .clickable {
                                        coroutineScope.launch {
                                            showDialog()
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Column {
                                    Image(
                                        painterResource(id = R.drawable.ic_household),
                                        contentDescription = null
                                    )
                                    Text("Household")
                                }
                            }
                        }
                        1 -> {
                            Box(
                                Modifier
                                    .background(Color(0x33FFED48))
                                    .fillMaxSize()
                                    .clickable {
                                        coroutineScope.launch {
                                            showDialog()
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Column {
                                    Image(
                                        painterResource(id = R.drawable.ic_shopping),
                                        contentDescription = null
                                    )
                                    Text("Shopping")
                                }
                            }
                        }
                        2 -> {
                            Box(
                                Modifier
                                    .background(Color(0x33BC40E7))
                                    .fillMaxSize()
                                    .clickable {
                                        coroutineScope.launch {
                                            showDialog()
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Column {
                                    Image(
                                        painterResource(id = R.drawable.ic_transport),
                                        contentDescription = null
                                    )
                                    Text("Transport")
                                }
                            }
                        }
                        3 -> {
                            Box(
                                Modifier
                                    .background(Color(0x33D9D9D9))
                                    .fillMaxSize()
                                    .clickable {
                                        coroutineScope.launch {
                                            showDialog()
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Column {
                                    Image(
                                        painterResource(id = R.drawable.ic_habits),
                                        contentDescription = null
                                    )
                                    Text("Personal Habits")
                                }
                            }
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