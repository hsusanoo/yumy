package com.hai.yumy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hai.yumy.ui.theme.gray50

@Composable
fun TagsSection(
    modifier: Modifier = Modifier,
    tags: List<String> = listOf(),
    onAdd: (String) -> Unit,
    onRemove: (String) -> Unit
) {

    var tag by remember {
        mutableStateOf("")
    }

    Column(modifier.fillMaxWidth()) {

        // Tags row
        LazyRow(Modifier.padding(bottom = 10.dp)) {
            items(tags) {
                Tag(text = it, showDelete = true, onDelete = { onRemove(it) }, scale = 2f)
            }
        }

        // Tags input
        Row(Modifier.fillMaxWidth()) {

            TextField(
                value = tag,
                onValueChange = { tag = it },
                maxLines = 4,
                textStyle = MaterialTheme.typography.body2,
                colors = TextFieldDefaults.textFieldColors(backgroundColor = gray50),
                placeholder = { Text(text = "Add tag...") },
                modifier = Modifier
                    .padding(end = 10.dp)
                    .weight(1f)
            )
            Icon(
                imageVector = Icons.Outlined.Check,
                contentDescription = "Add tag",
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        onAdd(tag)
                        tag = ""
                    }
                    .align(Alignment.CenterVertically)
                    .alpha(if (tag.isBlank()) .3f else 1f)
            )
        }
    }
}

@Preview
@Composable
fun TagsSectionPreview() {
    Column(
        Modifier
            .padding(10.dp)
            .background(Color.White)) {
        TagsSection(tags = listOf("Hello", "Healthy"), onAdd = {}, onRemove = {})
    }
}