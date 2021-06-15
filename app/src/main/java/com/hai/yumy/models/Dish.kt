package com.hai.yumy.models

import android.net.Uri

data class Dish(
    val image: Uri,
    val name: String,
    val description: String = "",
    val tags: List<String> = listOf(),
    val ingredients: List<Ingredient> = listOf(),
)
