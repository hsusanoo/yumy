package com.hai.yumy.models

data class Ingredient(

    //  Unformatted data
    var rawString: String? = null,

    // Formatted data
    var image: String? = null,
    var food: String? = null,
    var quantity: Number? = null,
    var unit: String? = null,
    var calories: Number? = null,

    ) {
    override fun toString(): String {
        return "Ingredient(rawString='$rawString', image=$image, food=$food, quantity=$quantity, unit=$unit, calories=$calories)"
    }
}
