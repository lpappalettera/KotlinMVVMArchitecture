package app.mvvm.architecture.ui.newsOverview

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.mvvm.architecture.model.NewsItem
import app.mvvm.architecture.sampleData.SampleData
import app.mvvm.architecture.sampleData.newsItems
import app.mvvm.architecture.ui.theme.NewsTheme

@Composable
fun NewsOverviewList(newsItems: List<NewsItem>, onNewsItemClick: (NewsItem) -> Unit) {
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

@Preview("News item row")
@Preview("News item row (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewNewsItemRow() {
    NewsTheme {
        NewsItemRow(
            newsItem = SampleData.newsItems.first(),
            onClick = {},
        )
    }
}

@Preview("News overview list", showBackground = true)
@Preview("News overview list (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview("News overview list (big font)", fontScale = 1.5f, showBackground = true)
@Composable
fun PreviewNewsOverviewList() {
    NewsTheme {
        NewsOverviewList(
            newsItems = SampleData.newsItems,
            onNewsItemClick = {},
        )
    }
}