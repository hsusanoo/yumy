package com.hai.yumy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hai.yumy.models.Ingredient
import com.hai.yumy.ui.theme.YumyTheme

@Composable
fun IngredientsSection(
    modifier: Modifier = Modifier,
    ingredientsList: List<Ingredient>,
    onAdd: (Ingredient) -> Unit,
    onRemove: (Ingredient) -> Unit
) {


    Column(modifier.padding(5.dp)) {

        if (ingredientsList.isNotEmpty()) {

            Card(
                elevation = 4.dp,
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            ) {

                Column(Modifier.padding(horizontal = 10.dp)) {
                    ingredientsList.forEach {
                        IngredientRow(
                            ingredient = it,
                            onRemove = { onRemove(it) },
//                    hasNext = true
                        )
                    }
                }
            }
        }

        IngredientInput(onAddIngredient = onAdd)
    }

}

@Composable
private fun IngredientRow(
    ingredient: Ingredient,
    onRemove: (Ingredient) -> Unit,
    modifier: Modifier = Modifier,
    hasNext: Boolean = false
) {

    println("Ingredient to show: ${ingredient.rawString}")

    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 10.dp),
    ) {

        Text(ingredient.rawString, Modifier.weight(1f))

        Icon(
            imageVector = Icons.Outlined.Remove,
            contentDescription = "Remove ingredient button",
            tint = LocalContentColor.current.copy(.6f),
            modifier = Modifier
                .clickable {
                    onRemove(ingredient)
                }
        )

    }
    if (hasNext)
        Divider()

}

@Composable
fun IngredientInput(
    onAddIngredient: (Ingredient) -> Unit,
    modifier: Modifier = Modifier
) {

    val (text, setText) = remember { mutableStateOf("") }

    Row(
        modifier
            .height(60.dp)
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {

        TextField(
            value = text,
            onValueChange = { setText(it) },
            placeholder = { Text(text = "1/2 L of milk") },
            textStyle = MaterialTheme.typography.body1,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
            singleLine = true,
            maxLines = 1,
            modifier = modifier
                .weight(1f)
                .padding(5.dp)
        )

        Button(
            onClick = {
                onAddIngredient(Ingredient(text))
                setText("")
            },
            shape = CircleShape,
            enabled = text.isNotBlank(),
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.CenterVertically),
        ) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = "Add Ingredient Button",
            )
        }

    }
}

@Preview
@Composable
private fun IngredientFieldsPreview() {

    YumyTheme {
        Column(
            Modifier
                .padding(10.dp)
                .background(Color.White)
        ) {

            IngredientsSection(
                ingredientsList = listOf(
                    Ingredient("1/2L of Milk"),
                    Ingredient("2 large Potatoes")
                ),
                onAdd = {},
                onRemove = {})
        }
    }
}

@Preview("Ingredient Input")
@Composable
private fun IngredientInputPreview() {
    YumyTheme {
        IngredientInput({})
    }
}