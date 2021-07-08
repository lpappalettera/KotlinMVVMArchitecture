package app.mvvm.architecture.ui.newsOverview

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import app.mvvm.architecture.R
import app.mvvm.architecture.model.NewsItem
import app.mvvm.architecture.sampleData.SampleData
import app.mvvm.architecture.sampleData.newsItems
import app.mvvm.architecture.ui.components.InsetAwareTopAppBar
import app.mvvm.architecture.ui.theme.NewsTheme
import app.mvvm.architecture.util.Resource
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun NewsOverviewScreen(
    viewModel: NewsOverviewViewModel = viewModel(),
    navigateToNewsItem: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    NewsOverviewScreen(
        uiState = uiState,
        onRefresh = viewModel::loadNews,
        navigateToNewsItem = navigateToNewsItem
    )
}

@Composable
fun NewsOverviewScreen(
    uiState: Resource<List<NewsItem>>,
    onRefresh: () -> Unit,
    navigateToNewsItem: (String) -> Unit,
) {
    val scaffoldState = rememberScaffoldState()

    uiState.error?.getContentIfNotHandled()?.let { error ->
        val errorMessage = stringResource(error.errorMessage)
        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar(errorMessage)
        }
    }

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        scaffoldState = scaffoldState,
        topBar = {
            InsetAwareTopAppBar(
                title = {
                    Text(text = stringResource(R.string.news_overview_title))
                },
            )
        },
    ) { innerPadding ->
        NewsOverviewContent(
            modifier = Modifier.padding(innerPadding),
            state = uiState,
            onRefresh = onRefresh,
            onNewsItemClick = { item -> navigateToNewsItem(item.id) },
        )
    }
}

@Composable
fun NewsOverviewContent(
    modifier: Modifier = Modifier,
    state: Resource<List<NewsItem>>,
    onRefresh: () -> Unit,
    onNewsItemClick: (NewsItem) -> Unit,
) {
    SwipeRefresh(
        modifier = modifier,
        state = rememberSwipeRefreshState(state is Resource.Loading),
        onRefresh = onRefresh,
    ) {
        val newsItems = state.data
        when {
            newsItems == null -> {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()))
            }
            newsItems.isEmpty() -> {
                NewsOverviewEmpty()
            }
            else -> {
                NewsOverviewList(
                    newsItems = state.data,
                    onNewsItemClick = onNewsItemClick
                )
            }
        }
    }
}

@Composable
fun NewsOverviewEmpty() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = stringResource(R.string.news_overview_empty))
    }
}

@Preview("News overview screen")
@Preview("News overview screen (dark)", uiMode = UI_MODE_NIGHT_YES)
@Preview("News overview screen (big font)", fontScale = 1.5f)
@Composable
fun PreviewNewsOverviewScreen() {
    NewsTheme {
        ProvideWindowInsets {
            NewsOverviewScreen(
                uiState = Resource.Success(SampleData.newsItems),
                onRefresh = {},
                navigateToNewsItem = {},
            )
        }
    }
}

@Preview("News overview screen (empty)")
@Composable
fun PreviewNewsOverviewScreenEmpty() {
    NewsTheme {
        ProvideWindowInsets {
            NewsOverviewScreen(
                uiState = Resource.Success(emptyList()),
                onRefresh = {},
                navigateToNewsItem = {},
            )
        }
    }
}