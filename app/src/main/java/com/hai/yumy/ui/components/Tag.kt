package com.hai.yumy.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Tag(
    text: String,
    modifier: Modifier = Modifier,
    showDelete: Boolean = false,
    onDelete: (String) -> Unit = {},
    scale: Float = 1f
) {

    Surface(
        color = Color.Black,
        modifier = modifier
            .padding(horizontal = (scale * 2).dp, vertical = (scale * 4).dp)
            .clip(RoundedCornerShape(50))
    ) {
        Row() {

            Text(
                text = text,
                color = Color.White,
                fontSize = (scale * 10).sp,
                modifier = Modifier
                    .padding(horizontal = 7.dp, vertical = 2.dp)
                    .align(Alignment.CenterVertically)
            )
            if (showDelete) {
                Icon(
                    imageVector = Icons.Outlined.Clear,
                    contentDescription = "Delete tag icon",
                    tint = Color.White,
                    modifier = Modifier
                        .size((scale * 15).dp)
                        .padding(end = (scale * 3).dp)
                        .align(Alignment.CenterVertically)
                        .clickable { onDelete(text) }
                )
            }
        }
    }
}

@Preview
@Composable
fun TagPreview() {
    Column(modifier = Modifier.padding(20.dp)) {
        Tag(text = "Hello", showDelete = true, scale = 1.5f)
        Tag(text = "Healthy")
        Tag(text = "Good", showDelete = true)
    }
}