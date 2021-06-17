package com.hai.yumy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hai.yumy.ui.YumyApp
import com.hai.yumy.ui.theme.YumyTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YumyTheme {
                YumyApp()
            }
        }
    }
}