package com.hai.yumy.ui

import androidx.compose.runtime.Composable
import com.hai.yumy.ui.theme.YumyTheme
import com.hai.yumy.ui.views.HomeView
import com.hai.yumy.utils.getRecipesFromFirebase
import com.hai.yumy.viewmodels.HomeVM
import com.hai.yumy.viewmodels.RecipeVM

@Composable
fun YumyApp() {
    val recipeVM = RecipeVM()
    val homeVM = HomeVM()

    getRecipesFromFirebase {
        homeVM.fillRecipesList(it)
    }

    // TODO: Navigation
    YumyTheme {
        HomeView(homeVM)
//        NewRecipeView(recipeVM = recipeVM)
    }

}