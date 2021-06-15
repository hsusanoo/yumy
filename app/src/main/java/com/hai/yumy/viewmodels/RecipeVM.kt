package com.hai.yumy.viewmodels

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hai.yumy.models.Ingredient


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

