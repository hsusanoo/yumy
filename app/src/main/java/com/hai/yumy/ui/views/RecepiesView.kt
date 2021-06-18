package com.hai.yumy.ui.views

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hai.yumy.ui.components.AppBar
import com.hai.yumy.ui.components.cards.RecipeCard
import com.hai.yumy.viewmodels.HomeVM
import com.hai.yumy.viewmodels.RecipeVM
import java.util.*

@ExperimentalMaterialApi
@Composable
fun HomeView(
    homeVM: HomeVM,
    recipeVM: RecipeVM = RecipeVM(),
    modifier: Modifier = Modifier,
    setCurrentView: (String) -> Unit
) {

    val (text, setText) = remember { mutableStateOf("") }
    val onValueChange = { value: String ->
        setText(value)
        homeVM.filterItems(value.lowercase(Locale.getDefault()))
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { setCurrentView("newDish") },
                backgroundColor = MaterialTheme.colors.primary,
                modifier = Modifier.padding(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "Add new dish"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = true
    ) {
        Column(modifier) {
            AppBar(
                title = "Recipes",
                text = text,
                onValueChange = onValueChange
            )
            LazyColumn {
                items(homeVM.recipes) {
                    RecipeCard(
                        recipe = it
                    ) {
                        recipeVM.image.value = Uri.parse(it.image)
                        recipeVM.description.value = it.description
                        recipeVM.name.value = it.name.toString()
                        recipeVM.preptimeH.value = it.preptimeH.toString()
                        recipeVM.preptimeM.value = it.preptimeM.toString()
                        recipeVM.ingredients.addAll(it.ingredients)
                        recipeVM.servings.value = it.servings.toString()
                        recipeVM.tags.addAll(it.tags)
                        setCurrentView("editDish")
                    }
                }
            }
        }
    }
}