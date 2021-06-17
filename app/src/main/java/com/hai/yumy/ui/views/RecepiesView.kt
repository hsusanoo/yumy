package com.hai.yumy.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hai.yumy.models.Recipe
import com.hai.yumy.ui.components.cards.AppBar

@Composable
fun RecipesView(recipes: List<Recipe>, modifier: Modifier = Modifier) {
    Column(modifier) {
        AppBar(
            title = "Recipes",
            menu_icon = Icons.Outlined.Menu,
            search_icon = Icons.Outlined.Search,
            menuOnClick = {},
            searchOnClick = {}
        )
        LazyColumn {
            items (recipes) {
                it
            }
        }
    }
}