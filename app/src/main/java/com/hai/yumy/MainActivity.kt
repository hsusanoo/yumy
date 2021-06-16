package com.hai.yumy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NoLiveLiterals
import androidx.compose.ui.Modifier
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

@NoLiveLiterals
@Composable
fun MyAppContent(modifier: Modifier = Modifier) {

}