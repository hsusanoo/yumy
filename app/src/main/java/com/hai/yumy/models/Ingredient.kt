package com.hai.yumy.models

data class Ingredient(

    //  Unformatted data
    var rawString: String,

    // Formatted data
    var image: String? = null,
    var food: String? = null,
    var quantity: Number? = null,
    var unit: String? = null,
    var calories: Number? = null,
    var weight: Number? = null // in grams
)
