package app.mvvm.architecture.ui.newsItem

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
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
fun NewsItemScreen(
    viewModel: NewsItemViewModel = viewModel(),
    navigateUp: () -> Unit,
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
                    Text(text = stringResource(R.string.news_item_title))
                },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.common_navigate_up)
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        NewsItemContent(
            modifier = Modifier.padding(innerPadding),
            state = state,
        )
    }
}

@Composable
fun NewsItemContent(
    modifier: Modifier = Modifier,
    state: Resource<NewsItem>,
) {
    SwipeRefresh(
        modifier = modifier,
        state = rememberSwipeRefreshState(state is Resource.Loading),
        swipeEnabled = false,
        onRefresh = { /* Refresh not enabled */ },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            if (state.data != null) {
                NewsItemText(newsItem = state.data)
            }
        }
    }
}

@Composable
fun NewsItemText(newsItem: NewsItem) {
    Text(text = newsItem.title, style = MaterialTheme.typography.h6)
    if (newsItem.description != null) {
        Text(text = newsItem.description, style = MaterialTheme.typography.subtitle1)
    }
    if (newsItem.content != null) {
        Text(text = newsItem.content, style = MaterialTheme.typography.body1)
    }
}

@Preview("News item screen")
@Preview("News item screen (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("News item screen (big font)", fontScale = 1.5f)
@Composable
fun PreviewNewsOverviewScreen() {
    NewsTheme {
        NewsItemScreen(navigateUp = {})
    }
}