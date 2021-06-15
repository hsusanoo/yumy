package com.hai.yumy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hai.yumy.models.Dish
import com.hai.yumy.models.DishesViewModel
import com.hai.yumy.ui.components.cards.DishCard
import com.hai.yumy.ui.components.cards.AppBar
import androidx.compose.runtime.livedata.observeAsState
import com.hai.yumy.data.getDishes

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<DishesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel.fillDishesList(getDishes())
        super.onCreate(savedInstanceState)
        setContent {
            MyAppContent(viewModel)
        }
    }
}

@NoLiveLiterals
@Composable
fun MyAppContent(
    viewModel: DishesViewModel,
    modifier: Modifier = Modifier
) {
    val items: List<Dish> by viewModel.dishes.observeAsState(listOf())
    val (text, setText) = remember { mutableStateOf("") }
    val onValueChange = { value: String ->
        println("Filtering")
        viewModel.filterItems(value)
        setText(value)
    }
    Column(modifier = modifier) {
        AppBar(
            title = "Recipes",
            text = text,
            menu_icon = R.drawable.menu_icon,
            search_icon = R.drawable.search_icon,
            onValueChange = onValueChange,
            onImeAction = {}
        )
        LazyColumn() {
            println("Size: ${items.size}")
            items(items = items) {
                DishCard(
                    image = it.image,
                    name = it.name,
                    prepDurationH = it.prepDurationH,
                    prepDurationM = it.prepDurationM,
                    tags = it.tags,
                    serving = it.serving
                )
            }
        }
    }
}