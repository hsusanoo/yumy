package com.hai.yumy.ui.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AppBar(
    title: String,
    menu_icon: ImageVector,
    search_icon: ImageVector,
    menuOnClick: () -> Unit,
    searchOnClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White,
            )
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        elevation = 60.dp,
        navigationIcon = {
            IconButton(onClick = menuOnClick) {
                Icon(
                    imageVector = menu_icon,
                    contentDescription = title,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        actions = {
            IconButton(onClick = searchOnClick) {
                Icon(
                    imageVector = search_icon,
                    contentDescription = title,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    )
}

@Composable
fun AppBarTitle(
    title: String,
    search_icon: Int?,
    searchOnClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .fillMaxSize(),
            Arrangement.Center
        ) {
            Text(
                text = title,
                color = Color.White,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxSize()
            )
        }
        if (search_icon != null)
            IconButton(onClick = searchOnClick) {
                Image(
                    painter = painterResource(id = search_icon),
                    contentDescription = "search",
                    modifier = Modifier.size(24.dp)
                )
            }
    }
}

@Composable
fun AppBarIcon(
    icon: Int,
    onClick: () -> Unit
) {

}
