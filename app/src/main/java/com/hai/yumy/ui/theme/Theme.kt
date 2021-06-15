package com.hai.yumy.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color.LightGray,
    primaryVariant = Color.LightGray,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Color(0xFF442C2E),
    primaryVariant = Color(0xFFCAA99F),
    secondary = Color(0xFF2C4442),
    onSecondary = Color.Black,
    onPrimary = Color.White,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun YumyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}