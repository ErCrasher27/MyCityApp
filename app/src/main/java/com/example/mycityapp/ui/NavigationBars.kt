package com.example.mycityapp.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mycityapp.data.local.LocalNavigationData.navigationsItems

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier
) {
    var size: Int
    NavigationBar(modifier = modifier.fillMaxWidth()) {
        for (navItem in navigationsItems) {
            if (navItem.isHomePage) {
                size = 35
            } else {
                size = 25
            }
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text,
                        modifier = modifier.size(size.dp)
                    )
                },
                selected = true,
                onClick = {}
            )
        }
    }
}