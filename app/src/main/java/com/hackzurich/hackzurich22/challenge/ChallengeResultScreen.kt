package com.hackzurich.hackzurich22.challenge

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackzurich.hackzurich22.R
import com.hackzurich.hackzurich22.components.Header
import com.hackzurich.hackzurich22.ui.theme.Gray
import com.hackzurich.hackzurich22.ui.theme.Primary

@Composable
fun ChallengeResultScreen(finish: () -> Unit) {
    Column() {
        Header()
        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)) {
                append("You saved 1700 litres!\n")
            }
            withStyle(style = SpanStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)) {
                append("Which can provide water to  ")
            }
            withStyle(
                style = SpanStyle(
                    color = Primary,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("10.5 people ")
            }
            withStyle(style = SpanStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)) {
                append("in Switzerland ")
            }
        }, modifier = Modifier.padding(16.dp))
        Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 0.dp)) {
            (0..10).forEach {
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    painterResource(id = R.drawable.ic_person),
                    contentDescription = null,
                    tint = Primary
                )
            }
        }
        Text(
            "0,00011% of Swiss population",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            style = TextStyle(color = Gray)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = finish) {
                Text("BACK TO HOME!")
            }
        }

    }
}