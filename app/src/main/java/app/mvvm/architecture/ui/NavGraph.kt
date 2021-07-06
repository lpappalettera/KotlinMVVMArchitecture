package app.mvvm.architecture.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import app.mvvm.architecture.ui.newsItem.NewsItemScreen
import app.mvvm.architecture.ui.newsOverview.NewsOverviewScreen

/**
 * Screens with their [route] used in the ([NewsApp]).
 */
sealed class Screen(val route: String) {
    object NewsOverview : Screen("news")
    data class NewsItem(val id: String) : Screen("news/item/$id") {
        object ArgKey {
            const val id = "id"
        }
    }
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.NewsOverview.route,
) {
    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(Screen.NewsOverview.route) {
            NewsOverviewScreen(
                viewModel = hiltViewModel(),
                navigateToNewsItem = actions.navigateToNewsItem
            )
        }
        composable(Screen.NewsItem("{${Screen.NewsItem.ArgKey.id}}").route) { backStackEntry ->
            NewsItemScreen(
                viewModel = hiltViewModel(backStackEntry),
                newsItemId =
                    requireNotNull(backStackEntry.arguments?.getString(Screen.NewsItem.ArgKey.id)),
                navigateUp = actions.navigateUp,
            )
        }
    }
}

/**
 * Models the navigation actions in the app.
 */
class MainActions(navController: NavHostController) {
    val navigateToNewsItem: (String) -> Unit = { newsItemId ->
        navController.navigate(Screen.NewsItem(newsItemId).route)
    }

    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }
}