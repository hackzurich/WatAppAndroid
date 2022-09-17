package com.hackzurich.hackzurich22.challenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.hackzurich.hackzurich22.R
import com.hackzurich.hackzurich22.components.Header
import com.hackzurich.hackzurich22.ui.theme.Font
import com.hackzurich.hackzurich22.ui.theme.Primary

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun PickChallengeScreen() {
    var openDialog by remember { mutableStateOf(false) }
    val changeOpenDialog: (Boolean) -> Unit = { openDialog = it }

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
                                        openDialog = true
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
                                        openDialog = true
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
                                        openDialog = true
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
                                        openDialog = true
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

        if (openDialog) {
            QuizDialog(changeOpenDialog, {})
        }
    }
}

@ExperimentalComposeUiApi
@Composable
private fun QuizDialog(changeOpenDialog: (Boolean) -> Unit, whatCanIDo: () -> Unit) {
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = { changeOpenDialog(false) }
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .background(Color.White)
                .fillMaxWidth()
        ) {
            Image(
                painterResource(id = R.drawable.showerhead),
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
                modifier = Modifier
                    .weight(1f, fill = false)
                    .fillMaxWidth()
            )
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = Font,
                            fontSize = 14.sp
                        )
                    ) {
                        append("The average family could save 10220 liters per year by installing WaterSense labeled showerheads. ")
                    }
                    withStyle(style = SpanStyle(color = Font, fontSize = 14.sp)) {
                        append("Since these water savings will reduce demands on water heaters, they will also save energy. In fact, the average family could save more than 330 kilowatt hours of electricity annually, enough to power a house for 11 days.")
                    }
                },
                modifier = Modifier.padding(16.dp),
            )
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                TextButton(onClick = { changeOpenDialog(false) }) {
                    Text("CLOSE", style = TextStyle(color = Color.Black))
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = whatCanIDo) {
                    Text("WHAT CAN I DO?")
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Preview
@Composable
fun PickChallengeScreenPreview() {
    PickChallengeScreen()
}