package com.hackzurich.hackzurich22

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackzurich.hackzurich22.components.Header
import com.hackzurich.hackzurich22.ui.theme.Gray
import com.hackzurich.hackzurich22.ui.theme.Primary

@Composable
fun HomeScreen() {
    Column() {
        Header()
        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)) {
                append("You saved ")
            }
            withStyle(
                style = SpanStyle(
                    color = Primary,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("20 000 litres\n")
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
                append("123.5 people ")
            }
            withStyle(style = SpanStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)) {
                append("in Switzerland ")
            }
        }, modifier = Modifier.padding(16.dp))
        Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 0.dp)) {
            Icon(
                painterResource(id = R.drawable.ic_person),
                contentDescription = null,
                tint = Primary
            )
            (0..16).forEach {
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    painterResource(id = R.drawable.ic_person),
                    contentDescription = null,
                    tint = Gray
                )
            }
        }
        Text(
            "0,00014% of population",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            style = TextStyle(color = Gray)
        )
    }
}

@Preview
@Composable
private fun WelcomeScreenPreview() {
    HomeScreen()
}