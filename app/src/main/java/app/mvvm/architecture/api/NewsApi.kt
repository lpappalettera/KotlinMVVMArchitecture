package app.mvvm.architecture.api

import app.mvvm.architecture.api.model.TopHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query(value = "country") country: String = "nl"): TopHeadlinesResponse

}