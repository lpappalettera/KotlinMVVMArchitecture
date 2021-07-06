package app.mvvm.architecture.ui.newsOverview

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.mvvm.architecture.R
import app.mvvm.architecture.model.NewsItem
import app.mvvm.architecture.ui.components.InsetAwareTopAppBar
import app.mvvm.architecture.ui.theme.NewsTheme
import app.mvvm.architecture.util.Resource
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun NewsOverviewScreen(
    viewModel: NewsOverviewViewModel = viewModel(),
    navigateToNewsItem: (String) -> Unit,
) {
    val state by viewModel.state.collectAsState()
    val scaffoldState = rememberScaffoldState()

    state.error?.getContentIfNotHandled()?.let { error ->
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
            state = state,
            onRefresh = viewModel::loadNews,
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
        if (state.data == null) {
            Box(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()))
        } else {
            NewsOverviewList(state.data, onNewsItemClick)
        }
    }
}

@Composable
fun NewsOverviewList(newsItems: List<NewsItem>, onNewsItemClick: (NewsItem) -> Unit) {
    if (newsItems.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = stringResource(R.string.news_overview_empty))
        }
    } else {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(newsItems) { newsItem ->
                NewsItemRow(
                    newsItem = newsItem,
                    onClick = { onNewsItemClick(newsItem) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsItemRow(newsItem: NewsItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp,
        onClick = onClick,
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(text = newsItem.title, style = MaterialTheme.typography.h6)
            if (newsItem.description != null) {
                Text(text = newsItem.description, style = MaterialTheme.typography.body1)
            }
        }
    }
}

@Preview("News overview screen")
@Preview("News overview screen (dark)", uiMode = UI_MODE_NIGHT_YES)
@Preview("News overview screen (big font)", fontScale = 1.5f)
@Composable
fun PreviewNewsOverviewScreen() {
    NewsTheme {
        NewsOverviewScreen(navigateToNewsItem = {})
    }
}