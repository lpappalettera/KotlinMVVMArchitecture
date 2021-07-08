package app.mvvm.architecture.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopHeadlinesResponse(
   val status: String,
   @SerialName("articles")
   val newsItems: List<NewsItemDto>
)