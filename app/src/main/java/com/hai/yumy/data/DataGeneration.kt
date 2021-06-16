package com.hai.yumy.data

import com.hai.yumy.R
import com.hai.yumy.models.Dish

fun getDishes(): List<Dish> {
    var PastaWhiteSauce = Dish(
        name = "Pasta",
        image = R.drawable.pasta_white_sauce,
        prepDurationH = 0,
        prepDurationM = 20,
        tags = listOf("Healthy", "Easy"),
        serving = 4,
        description = "Pasta with white sauce",
        ingredients = listOf()
    )
    var Tangia = Dish(
        image = R.drawable.tangia,
        name = "Tangia",
        prepDurationH = 2,
        prepDurationM = 20,
        tags = List(1) { "Traditional" },
        serving = 5,
        description = "Tangia",
        ingredients = listOf()
    )
    var ChickenWings = Dish(
        image = R.drawable.chicken_wings,
        name = "Chicken wings",
        prepDurationH = 1,
        prepDurationM = 20,
        tags = List<String>(1) { "Chicken" },
        serving = 6,
        description = "Chicken wings",
        ingredients = listOf()
    )
    var dishes = listOf<Dish>(PastaWhiteSauce, Tangia, ChickenWings);
    return dishes
}