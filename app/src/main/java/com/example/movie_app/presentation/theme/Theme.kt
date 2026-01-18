package com.example.movie_app.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = TextForDark,
    onPrimary = DarkBack ,
    background = DarkBack ,
    onBackground = TextForDark,
    surface = DarkSurface,
    onSurface = TextForDark
)

private val LightColorScheme = lightColorScheme(
    primary = TextForLight,
    onPrimary = White ,
    background = LightBack ,
    onBackground = TextForLight,
    surface = LightSurface,
    onSurface = TextForLight
)



@Composable
fun Movie_appTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    //todo побавитись із статус баром

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}