package com.hai.yumy.ui.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AppBar(
    title: String,
    text: String,
    menu_icon: Int,
    search_icon: Int,
    onValueChange: (String) -> Unit = {},
    onImeAction: () -> Unit = {}
) {

    var showTextField = remember {
        mutableStateOf(false)
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceEvenly) {
        IconButton(onClick = {}) {
            Image(
                painter = painterResource(id = menu_icon),
                contentDescription = "Menu",
                modifier = Modifier.size(24.dp)
            )
        }
        if (showTextField.value) {
            TextField(
                value = text,
                onValueChange = { newValue ->
                    onValueChange(newValue)
                },
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                maxLines = 1,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onImeAction()
                        showTextField.value = !showTextField.value
                        keyboardController?.hide()
                    }),
                modifier = Modifier.weight(0.9f)
            )
        } else {
            Text(
                text = title,
                Modifier
                    .weight(0.9f)
                    .padding(10.dp)
                    .height(IntrinsicSize.Max),
                fontSize = 20.sp
            )
            IconToggleButton(
                checked = false,
                onCheckedChange = {
                    showTextField.value = !showTextField.value
                }) {
                Image(
                    painter = painterResource(id = search_icon),
                    contentDescription = "Search",
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }
    }
}
