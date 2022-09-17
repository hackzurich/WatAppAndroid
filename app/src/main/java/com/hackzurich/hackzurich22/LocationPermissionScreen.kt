package com.hackzurich.hackzurich22

import android.Manifest
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.hackzurich.hackzurich22.components.Header
import com.hackzurich.hackzurich22.ui.theme.Primary

@ExperimentalPermissionsApi
@Composable
fun LocationPermissionScreen(done: () -> Unit) {
    val locationPermissionsState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Header()
        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)) {
                append("Give us access to your current location to help you ")
            }
            withStyle(
                style = SpanStyle(
                    color = Primary,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("save even more water!")
            }
        }, modifier = Modifier.padding(16.dp))
        Spacer(modifier = Modifier.height(100.dp))
        Button(onClick = {
            locationPermissionsState.launchMultiplePermissionRequest()
            done()
        }) {
            Text("LET'S DO IT!")
        }
        TextButton(onClick = done) {
            Text("I'd rather not..")
        }
    }
}