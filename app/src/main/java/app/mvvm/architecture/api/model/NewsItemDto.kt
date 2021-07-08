package app.mvvm.architecture.api.model

import app.mvvm.architecture.model.NewsItem
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.OffsetDateTime

@Serializable
data class NewsItemDto(
        val author: String?,
        val title: String,
        val description: String?,
        val url: String,
        val urlToImage: String?,
        @Contextual
        val publishedAt: OffsetDateTime,
        val content: String?
) {
    fun toNewsItem() = NewsItem(
        author ?: "", title, description, url, urlToImage, publishedAt, content
    )
}