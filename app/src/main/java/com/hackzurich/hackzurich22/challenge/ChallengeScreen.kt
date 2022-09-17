package com.hackzurich.hackzurich22.challenge

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.hackzurich.hackzurich22.R
import com.hackzurich.hackzurich22.components.Header
import com.hackzurich.hackzurich22.ui.theme.Font
import com.hackzurich.hackzurich22.ui.theme.HackZurich22Theme

@ExperimentalPagerApi
@Composable
fun ChallengeScreen(answerChallenge: () -> Unit) {
    BackHandler {}

    Column() {
        Header()
        Text(
            "What can I choose instead?",
            style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(16.dp)
        )
        HorizontalPager(count = 2, contentPadding = PaddingValues(end = 32.dp)) { page ->
            // Our page content
            Box(modifier = Modifier.padding(16.dp)) {
                if (page == 0) {
                    Card(elevation = 2.dp, modifier = Modifier.clip(RoundedCornerShape(4.dp))) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                "One apple needs 125 litres",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Font
                                ),
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Image(
                                painterResource(id = R.drawable.apple),
                                contentDescription = null,
                                modifier = Modifier.clip(
                                    RoundedCornerShape(8.dp)
                                )
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                OutlinedButton(onClick = answerChallenge) {
                                    Text("SOUNDS GOOD!")
                                }
                            }
                        }
                    }
                } else {
                    Card(elevation = 2.dp, modifier = Modifier.clip(RoundedCornerShape(4.dp))) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                "Cheries need 181 liters per serving",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Font
                                ),
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Image(
                                painterResource(id = R.drawable.cherries),
                                contentDescription = null,
                                modifier = Modifier.clip(
                                    RoundedCornerShape(8.dp)
                                )
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                OutlinedButton(onClick = answerChallenge) {
                                    Text("SOUNDS GOOD!")
                                }
                            }
                        }
                    }
                }

            }
        }
        TextButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
            Text("Ok, but maybe another time", style = TextStyle(color = Color.Gray))
        }
    }
}

@ExperimentalPagerApi
@Preview
@Composable
fun ChallengeScreenPreview() {
    HackZurich22Theme() {
        ChallengeScreen {

        }
    }
}