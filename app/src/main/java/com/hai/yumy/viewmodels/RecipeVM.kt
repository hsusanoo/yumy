package com.hai.yumy.viewmodels

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hai.yumy.models.Ingredient
import com.hai.yumy.models.Recipe


class RecipeVM : ViewModel() {
    var image = mutableStateOf<Uri?>(null)
    var name = mutableStateOf(String())                 // Required
    var description = mutableStateOf(String())          // Required
    var tags = mutableStateListOf<String>()
    var preptimeH = mutableStateOf(String())
    var preptimeM = mutableStateOf(String())
    var servings = mutableStateOf(String())
    var ingredients = mutableStateListOf<Ingredient>()  // Required

    fun addIngredient(ingredient: Ingredient) {
        //  TODO: API Call to format data
        ingredients.add(ingredient)
    }

    fun removeIngredient(ingredient: Ingredient) {
        ingredients.remove(ingredient)
    }

    fun addTag(tag: String) {
        if (tag.isNotBlank()) tags.add(tag)
    }

    fun removeTag(tag: String) {
        tags.remove(tag)
    }

    fun isRequiredFieldsFilled(): Boolean {
        return name.value.isNotBlank() && description.value.isNotBlank() && ingredients.isNotEmpty()
    }

    /**
     * Clear all fields
     */
    fun clear() {
        image.value = null
        name.value = ""
        description.value = ""
        ingredients.clear()
        tags.clear()
        preptimeH.value = ""
        preptimeM.value = ""
        servings.value = ""
    }

    /**
     * Copy recipe data to ViewModel
     */
    fun copy(recipe: Recipe?) {
        if (recipe == null) return

        image.value = Uri.parse(recipe.image)
        name.value = recipe.name.toString()
        description.value = recipe.description
        tags.clear()
        tags.addAll(recipe.tags)
        ingredients.clear()
        ingredients.addAll(recipe.ingredients)
        preptimeH.value = recipe.preptimeH.toString()
        preptimeM.value = recipe.preptimeM.toString()
        servings.value = recipe.servings.toString()
    }

    override fun toString(): String {
        return "DishVM(image=${image.value}, " +
                "name=${name.value}, " +
                "description=${description.value}, " +
                "tags=${tags.joinToString(", ")}, " +
                "preptimeH=${preptimeH.value}, " +
                "preptimeM=${preptimeM.value}, " +
                "ingredients=${ingredients.joinToString(", ")})"
    }
}

