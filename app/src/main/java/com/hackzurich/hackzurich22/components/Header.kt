package com.hackzurich.hackzurich22.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackzurich.hackzurich22.R
import com.hackzurich.hackzurich22.ui.theme.Primary

@Composable
fun Header() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Primary,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Wat")
                }
                withStyle(style = SpanStyle(fontSize = 36.sp, fontWeight = FontWeight.Bold)) {
                    append("App")
                }
            }
        )

        Icon(
            painterResource(id = R.drawable.logo),
            contentDescription = null,
            tint = Primary,
            modifier = Modifier.size(60.dp)
        )
    }
}