package com.techethic.compose.dailycounter.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Colors
import androidx.wear.compose.material.MaterialTheme

private val ColorPalette = Colors(
    primary = Primary,
    primaryVariant = PrimaryVariant,
    secondary = Secondary,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    /* Other default colors to override
    background = Color.White,
    surface = Color.White,

    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun TestTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = ColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}