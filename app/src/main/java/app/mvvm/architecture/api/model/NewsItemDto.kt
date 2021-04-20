package app.mvvm.architecture.api.model

import app.mvvm.architecture.model.NewsItem
import com.squareup.moshi.JsonClass
import java.time.OffsetDateTime

@JsonClass(generateAdapter = true)
data class NewsItemDto(
        val author: String?,
        val title: String,
        val description: String?,
        val url: String,
        val urlToImage: String?,
        val publishedAt: OffsetDateTime,
        val content: String?
) {
    fun toNewsItem() = NewsItem(
        author ?: "", title, description, url, urlToImage, publishedAt, content
    )
}