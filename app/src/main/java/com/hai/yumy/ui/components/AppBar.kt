package com.hai.yumy.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AppBar(
    title: String,
    text: String,
    onValueChange: (String) -> Unit = {},
//    onImeAction: () -> Unit = {}
) {

    val showTextField = remember {
        mutableStateOf(false)
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceEvenly) {
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
//                        onImeAction()
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
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Menu Icon",
                    modifier =
                    Modifier.size(24.dp)
                )
            }
        }
    }
}
