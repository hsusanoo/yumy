package com.hai.yumy.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hai.yumy.R
import com.hai.yumy.ui.components.cards.AppBar
import com.hai.yumy.ui.components.cards.DishCard

@Composable
fun RecipesView(modifier: Modifier = Modifier) {
    Column(modifier) {
        AppBar(
            title = "Recipes",
            menu_icon = Icons.Outlined.Menu,
            search_icon = Icons.Outlined.Search,
            menuOnClick = {},
            searchOnClick = {}
        )
        LazyColumn {
            item {
                DishCard(
                    image = R.drawable.pasta_white_sauce,
                    name = "Pasta with white sauce",
                    prepDurationH = 0,
                    prepDurationM = 20,
                    tags = List(1) { "Healthy" },
                    serving = 4
                )
            }
            item {
                DishCard(
                    image = R.drawable.tangia,
                    name = "Tangia",
                    prepDurationH = 2,
                    prepDurationM = 20,
                    tags = List(1) { "Traditional" },
                    serving = 5
                )
            }
            item {
                DishCard(
                    image = R.drawable.chicken_wings,
                    name = "Chicken wings",
                    prepDurationH = 1,
                    prepDurationM = 20,
                    tags = List<String>(1) { "Chicken" },
                    serving = 6
                )
            }
            item {
                DishCard(
                    image = R.drawable.tinola,
                    name = "Tinola",
                    prepDurationH = 1,
                    prepDurationM = 20,
                    tags = List(1) { "Filipino" },
                    serving = 6
                )
            }
            item {
                DishCard(
                    image = R.drawable.tortilla,
                    name = "Chicken wings",
                    prepDurationH = 1,
                    prepDurationM = 20,
                    tags = List(1) { "Latin" },
                    serving = 6
                )
            }
        }
    }
}