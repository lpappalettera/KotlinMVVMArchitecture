package app.mvvm.architecture

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.mvvm.architecture.ui.MainActivity
import app.mvvm.architecture.ui.newsOverview.NewsOverviewScreen
import app.mvvm.architecture.util.Resource
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsOverviewScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun emptyStateIsWhenNoNewsItemsAreAvailable() {
        composeTestRule.setContent {
            NewsOverviewScreen(
                uiState = Resource.Success(emptyList()),
                onRefresh = {},
                navigateToNewsItem = {}
            )
        }

        composeTestRule
            .onNodeWithText(composeTestRule.activity.getString(R.string.news_overview_empty))
            .assertExists()
    }

    @Test
    fun newsItemsAreShown() {
        composeTestRule.setContent {
            NewsOverviewScreen(
                uiState = Resource.Success(TestData.newsItems),
                onRefresh = {},
                navigateToNewsItem = {}
            )
        }

        composeTestRule.onNodeWithText(TestData.newsItems.first().title).assertExists()
        composeTestRule.onNodeWithText(TestData.newsItems.last().title).assertExists()
    }

    @Test
    fun navigateByClickingOnANewsItem() {
        var navigateToItemId: String? = null

        composeTestRule.setContent {
            NewsOverviewScreen(
                uiState = Resource.Success(TestData.newsItems),
                onRefresh = {},
                navigateToNewsItem = {
                    navigateToItemId = it
                }
            )
        }

        composeTestRule.onNodeWithText(TestData.newsItems.first().title).performClick()

        Assert.assertEquals(TestData.newsItems.first().id, navigateToItemId)
    }

    @Test
    fun refreshNewsItemsByPullToRefresh() {
        var refreshed = false

        composeTestRule.setContent {
            NewsOverviewScreen(
                uiState = Resource.Success(TestData.newsItems),
                onRefresh = {
                    refreshed = true
                },
                navigateToNewsItem = {}
            )
        }

        composeTestRule.onRoot().performGesture {
            swipeDown()
        }

        Assert.assertTrue(refreshed)
    }
}