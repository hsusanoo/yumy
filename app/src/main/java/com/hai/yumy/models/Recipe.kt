package com.hai.yumy.models

data class Recipe(
    var id: String? = null,
    var image: String,
    var name: String,
    var description: String = "",
    var tags: List<String> = listOf(),
    var ingredients: List<Ingredient> = listOf(),
    var preptimeH: Int,
    var preptimeM: Int,
    var servings: Int,
) {
    override fun toString(): String {
        return "Recipe(image='$image', " +
                "name='$name', " +
                "description='$description', " +
                "tags=${tags.joinToString(", ")}, " +
                "ingredients=$ingredients, " +
                "preptimeH=$preptimeH, " +
                "preptimeM=$preptimeM, " +
                "servings=$servings)"
    }
}
