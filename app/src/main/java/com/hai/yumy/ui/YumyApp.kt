package com.hai.yumy.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.hai.yumy.ui.theme.YumyTheme
import com.hai.yumy.ui.views.HomeView
import com.hai.yumy.ui.views.NewRecipeView
import com.hai.yumy.utils.getRecipesFromFirebase
import com.hai.yumy.viewmodels.HomeVM
import com.hai.yumy.viewmodels.RecipeVM

@ExperimentalMaterialApi
@Composable
fun YumyApp() {
    var recipeVM = remember { mutableStateOf(RecipeVM()).value }
    val homeVM = HomeVM()
    val (currentView, setCurrentView) = remember { mutableStateOf("home") }
    getRecipesFromFirebase {
        homeVM.fillRecipesList(it)
    }

    // TODO: Navigation
    YumyTheme {
        if (currentView == "home") {
            HomeView(
                homeVM = homeVM,
                recipeVM = recipeVM,
                setCurrentView = setCurrentView
            )
        } else if (currentView == "newDish") {
            recipeVM = RecipeVM()
            NewRecipeView(
                recipeVM = recipeVM,
                setCurrentView = setCurrentView,
                mode = "create"
            )
        } else if (currentView == "editDish") {
            NewRecipeView(
                recipeVM = recipeVM,
                setCurrentView = setCurrentView,
                mode = "update"
            )
        }

//        NewRecipeView(recipeVM = recipeVM)
    }

}