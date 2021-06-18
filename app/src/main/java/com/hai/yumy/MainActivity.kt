package com.hai.yumy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import com.hai.yumy.ui.YumyApp
import com.hai.yumy.ui.theme.YumyTheme
import com.hai.yumy.utils.getRecipesFromFirebase

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YumyTheme {
                YumyApp()
            }
        }
    }
}