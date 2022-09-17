package com.hackzurich.hackzurich22

import android.Manifest
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.hackzurich.hackzurich22.ui.theme.HackZurich22Theme

@ExperimentalPermissionsApi
@Composable
fun PushPermission() {
    if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
        val cameraPermissionState = rememberPermissionState(Manifest.permission.POST_NOTIFICATIONS)
        if (cameraPermissionState.status.isGranted) {
            Text("Camera permission Granted")
        } else {
            Column {
                val textToShow = if (cameraPermissionState.status.shouldShowRationale) {
                    "The camera is important for this app. Please grant the permission."
                } else {
                    "Camera not available"
                }

                Text(textToShow)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    cameraPermissionState.launchPermissionRequest()
                }) {
                    Text("Request permission")
                }
            }
        }
    } else {
        Text("Just go ahead, nothing to see")
    }
}

@ExperimentalPermissionsApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HackZurich22Theme {
        PushPermission()
    }
}