package com.hai.yumy.ui

import androidx.compose.runtime.Composable
import com.hai.yumy.ui.theme.YumyTheme
import com.hai.yumy.viewmodels.RecipeVM

@Composable
fun YumyApp() {
    val recipeVM = RecipeVM()
    // TODO: Navigation
    YumyTheme {
//        RecipesView()
//        NewRecipeView(recipeVM = recipeVM)
    }
}