package com.example.nallanudi.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(

    primary = Primary,
    secondary = Secondary,

    background = Background,
    surface = CardColor,

    onPrimary = androidx.compose.ui.graphics.Color.White,
    onBackground = TextPrimary,
    onSurface = TextPrimary
)

@Composable
fun NallaNudiTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography(),
        content = content
    )
}