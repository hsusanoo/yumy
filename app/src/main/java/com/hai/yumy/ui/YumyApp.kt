package com.hai.yumy.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.hai.yumy.ui.theme.YumyTheme
import com.hai.yumy.ui.views.HomeView
import com.hai.yumy.ui.views.NewRecipeView
import com.hai.yumy.utils.getRecipesFromFirebase
import com.hai.yumy.viewmodels.HomeVM
import com.hai.yumy.viewmodels.RecipeVM

@ExperimentalMaterialApi
@Composable
fun YumyApp() {

    val navController = rememberNavController()

    val recipeVM = RecipeVM()
    val homeVM = HomeVM()

    getRecipesFromFirebase {
        homeVM.fillRecipesList(it)
    }

    YumyTheme {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") { HomeView(homeVM, navController) }
            composable(
                "newRecipe?recipeId={recipeId}",
                arguments = listOf(navArgument("recipeId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                })
            ) {
                NewRecipeView(
                    recipeVM = recipeVM,
                    navController = navController,
                    recipeId = it.arguments?.getString("recipeId")
                )
            }
        }
    }

}