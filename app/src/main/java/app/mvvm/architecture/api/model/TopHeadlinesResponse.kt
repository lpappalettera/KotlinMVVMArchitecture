package app.mvvm.architecture.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TopHeadlinesResponse(
   val status: String,
   @Json(name = "articles")
   val newsItems: List<NewsItemDto>
)