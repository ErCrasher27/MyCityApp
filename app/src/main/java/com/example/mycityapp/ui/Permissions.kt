package com.example.mycityapp.ui

import android.content.Context
import android.content.Intent
import android.provider.Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.*

@OptIn(ExperimentalPermissionsApi::class)
fun PermissionState.isPermanentlyDenied(): Boolean {
    return !status.isGranted && !status.shouldShowRationale
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable

fun RequestPermissions(
    modifier: Modifier = Modifier,
    context: Context,
    namePermission: String,
    permission: String,
) {
    val permissionState =
        rememberPermissionState(permission = permission)

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(
        key1 = lifecycleOwner,
        effect = {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_RESUME) {
                    permissionState.launchPermissionRequest()
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        })

    CheckPermissions(
        namePermission = namePermission,
        permissionState = permissionState,
        permission = permission,
        context = context,
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CheckPermissions(
    modifier: Modifier = Modifier,
    context: Context,
    permissionState: PermissionState,
    permission: String,
    namePermission: String,
) {

    when (permissionState.permission) {
        permission -> {
            when {
                permissionState.status.isGranted -> {

                }
                permissionState.status.shouldShowRationale -> {
                }
                permissionState.isPermanentlyDenied() -> {
                    DialogPermanentlyDeniedRequestPermission(
                        title = "$namePermission Permission",
                        content = "$namePermission permission was permanently denied. You can enable it in the app setting",
                        context = context
                    )
                }
                else -> {
                }
            }
        }
    }

}

@Composable
fun DialogPermanentlyDeniedRequestPermission(
    modifier: Modifier = Modifier,
    context: Context,
    title: String,
    content: String
) {
    Column(modifier = Modifier.fillMaxSize()) {
        val openDialog = remember { mutableStateOf(true) }
        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                title = {
                    Text(text = title)
                },
                text = {
                    Text(text = content)
                },
                confirmButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                        onClick = {
                            openDialog.value = false
                            val i = Intent(ACTION_MANAGE_APPLICATIONS_SETTINGS)
                            context.startActivity(i)
                        }) {
                        Text("Go to settings")
                    }
                },
                dismissButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primaryContainer),
                        onClick = {
                            openDialog.value = false
                        }) {
                        Text("Ignore")
                    }
                }
            )
        }
    }
}
