package app.mvvm.architecture.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.navigation.compose.rememberNavController
import app.mvvm.architecture.ui.theme.NewsTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController

// TODO: Fix previews (with sample data)
// Add tests
// Remove unused dependencies
// Remove unused utilities

@Composable
fun NewsApp() {
    NewsTheme {
        ProvideWindowInsets {
            val systemUiController = rememberSystemUiController()
            val systemBarColor = MaterialTheme.colors.primaryVariant
            SideEffect {
                systemUiController.setStatusBarColor(systemBarColor)
            }

            val navController = rememberNavController()
            NavGraph(navController = navController)
        }
    }
}