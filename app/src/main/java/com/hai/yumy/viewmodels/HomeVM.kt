package com.hai.yumy.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.hai.yumy.models.Recipe

class HomeVM : ViewModel() {
    var recipes = mutableStateListOf<Recipe>()

    fun fillRecipesList(recipes: List<Recipe>) {
        this.recipes.clear()
        this.recipes.addAll(recipes)
    }

    fun filterItems(filter: String) {
        fillRecipesList(this.recipes)

        if (filter.isBlank())
            return

        recipes = recipes.toList().filter {
            it.name!!.contains(filter) || it.description.contains(filter)
        }.toMutableStateList()
    }

}