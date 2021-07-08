package app.mvvm.architecture.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    onPrimary = Color.White,
    secondary = Teal200,
    secondaryVariant = Teal700,
    onSecondary = Color.Black,
)

private val DarkThemeColors = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    onPrimary = Color.White,
    secondary = Teal200,
    secondaryVariant = Teal200,
    onSecondary = Color.Black,
)

@Composable
fun NewsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
        typography = NewsTypography,
        shapes = NewsShapes,
        content = content
    )
}