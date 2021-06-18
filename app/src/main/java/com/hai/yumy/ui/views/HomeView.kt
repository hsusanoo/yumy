package com.hai.yumy.ui.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.hai.yumy.models.Recipe
import com.hai.yumy.ui.components.AppBar
import com.hai.yumy.ui.components.cards.RecipeCard
import com.hai.yumy.viewmodels.HomeVM
import java.util.*

@ExperimentalMaterialApi
@Composable
fun HomeView(homeVM: HomeVM, navController: NavHostController, modifier: Modifier = Modifier) {

    val (text, setText) = remember { mutableStateOf("") }
    val onValueChange = { value: String ->
        println("Filtering")
        homeVM.filterItems(value.lowercase(Locale.getDefault()))
        setText(value)
    }

    fun navigateToRecipe(recipeId: String? = null) {
        navController.navigate("newRecipe?recipeId=$recipeId")
    }

    Scaffold(modifier,
        topBar = {
            AppBar(
                title = "Recipes",
                text = text,
                onValueChange = onValueChange
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navigateToRecipe() }) {
                Icon(
                    imageVector = Icons.Outlined.Add, contentDescription = "Add Recipe button",
                    tint = Color.White
                )
            }
        }
    ) {
        LazyColumn {
            items(homeVM.recipes) { recipe ->
                RecipeCard(
                    recipe = recipe,
                    onClick = { navigateToRecipe(it.id) }
                )
            }
        }
    }
}