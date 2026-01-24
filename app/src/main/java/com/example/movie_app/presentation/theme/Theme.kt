package com.example.movie_app.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = DarkBack,
    onPrimary =  TextForDark,
    background = DarkBack ,
    onBackground = TextForDark,
    surface = DarkSurface,
    onSurface = TextForDark
)

private val LightColorScheme = lightColorScheme(
    primary = LightBack,
    onPrimary = TextForLight ,
    background = LightBack ,
    onBackground = TextForLight,
    surface = LightSurface,
    onSurface = TextForLight
)



@Composable
fun Movie_appTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    //todo: experiment with the status bar

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}