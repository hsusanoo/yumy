package com.hai.yumy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NoLiveLiterals
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hai.yumy.ui.components.cards.DishCard
import com.hai.yumy.ui.theme.YumyTheme
import com.hai.yumy.ui.components.cards.AppBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppContent()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    YumyTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@NoLiveLiterals
@Composable
fun MyAppContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        AppBar(
            title = "Recipes",
            menu_icon = R.drawable.menu_icon,
            search_icon = R.drawable.search_icon,
            menuOnClick = {},
            searchOnClick = {}
        )
        LazyColumn() {
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
                    tags = List<String> (1){ "Chicken" },
                    serving = 6
                )
            }
            item {
                DishCard(
                    image = R.drawable.tinola,
                    name = "Tinola",
                    prepDurationH = 1,
                    prepDurationM = 20,
                    tags = List(1) { "Filipino"},
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MyAppContent()
    }
}