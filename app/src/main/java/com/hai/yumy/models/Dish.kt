package com.hai.yumy.models

data class Dish(
    var name: String,
    var image: Int,
    var description: String,
    var ingredients: List<Ingredient>,
    var tags: List<String>,
    var prepDurationH: Int,
    var prepDurationM: Int,
    var serving: Int
)