package com.hai.yumy.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.hai.yumy.ui.components.AppBar
import com.hai.yumy.ui.components.cards.RecipeCard
import com.hai.yumy.viewmodels.HomeVM

@Composable
fun HomeView(homeVM: HomeVM, modifier: Modifier = Modifier) {

    val (text, setText) = remember { mutableStateOf("") }
    val onValueChange = { value: String ->
        println("Filtering")
        homeVM.filterItems(value)
        setText(value)
    }

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
                )
            }
        }
    }
}