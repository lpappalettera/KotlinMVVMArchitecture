package app.mvvm.architecture

import app.mvvm.architecture.model.NewsItem
import java.time.OffsetDateTime

object TestData {

    val newsItems = listOf(
        NewsItem(
            author = "author1",
            title = "title1",
            description = "description1",
            url = "https://test.test",
            urlToImage = "https://test.test/image1",
            publishedAt = OffsetDateTime.now(),
            content = "Lorem ipsum"
        ),
        NewsItem(
            author = "author2",
            title = "title2",
            description = "description2",
            url = "https://test.test",
            urlToImage = "https://test.test/image2",
            publishedAt = OffsetDateTime.now().plusDays(1),
            content = null
        ),
        NewsItem(
            author = "author3",
            title = "title3",
            description = "description3",
            url = "https://test.test",
            urlToImage = "https://test.test/image3",
            publishedAt = OffsetDateTime.now().plusDays(2),
            content = "Lorem ipsum"
        ),
        NewsItem(
            author = "author4",
            title = "title4",
            description = "description4",
            url = "https://test.test",
            urlToImage = "https://test.test/image4",
            publishedAt = OffsetDateTime.now().plusDays(3),
            content = "Lorem ipsum"
        ),
        NewsItem(
            author = "author5",
            title = "title5",
            description = "description1",
            url = "https://test.test",
            urlToImage = "https://test.test/image1",
            publishedAt = OffsetDateTime.now().plusDays(4),
            content = "Lorem ipsum"
        ),
    )

}