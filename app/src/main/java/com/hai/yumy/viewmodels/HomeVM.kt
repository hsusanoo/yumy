package com.hai.yumy.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.hai.yumy.models.Recipe
import com.hai.yumy.utils.getRecipesFromFirebase
import java.util.*

class HomeVM : ViewModel() {
    var recipes = mutableStateListOf<Recipe>()

    fun fillRecipesList(recipes: List<Recipe>) {
        this.recipes.clear()
        this.recipes.addAll(recipes)
    }

    fun filterItems(filter: String) {

        getRecipesFromFirebase { _recipes ->
            fillRecipesList(_recipes)

            if (filter.isNotBlank()) {
                recipes = recipes.toList().filter {
                    it.name!!.lowercase(Locale.getDefault()).contains(filter)
                            || it.description.lowercase(Locale.getDefault()).contains(filter)
                            || isRecipeTagsContain(it.tags, filter)
                }.toMutableStateList()
            }
        }
    }

    private fun isRecipeTagsContain (tags: List<String>, filter: String): Boolean {

        for (tag in tags) {
            if (tag.lowercase(Locale.getDefault()).contains(filter))
                return true
        }

        return false
    }

}