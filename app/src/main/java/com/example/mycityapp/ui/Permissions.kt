package com.example.mycityapp.ui

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.*

@OptIn(ExperimentalPermissionsApi::class)
fun PermissionState.isPermanentlyDenied(): Boolean {
    return !status.isGranted && !status.shouldShowRationale
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun requestPermissions(modifier: Modifier = Modifier) {
    val permissionsState =
        rememberMultiplePermissionsState(permissions = listOf(Manifest.permission.CALL_PHONE))

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        permissionsState.permissions.forEach { perm ->
            when (perm.permission) {
                Manifest.permission.CALL_PHONE -> {
                    when {
                        perm.status.isGranted -> {
                            Text(text = "Phone Call permission accepted")
                        }
                        perm.status.shouldShowRationale -> {
                            Text(text = "Phone Call is needed to call place that you want book")
                        }
                        !perm.isPermanentlyDenied() -> {
                            Text(text = "phone Call permission was permanently denied. You can enable it in the app setting")
                        }
                    }
                }
            }
        }
    }
}