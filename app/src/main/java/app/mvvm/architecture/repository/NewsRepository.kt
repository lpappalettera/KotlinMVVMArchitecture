package app.mvvm.architecture.repository

import app.mvvm.architecture.api.NewsApi
import app.mvvm.architecture.database.dao.NewsDao
import app.mvvm.architecture.model.NewsItem
import app.mvvm.architecture.util.RateLimiter
import app.mvvm.architecture.util.Resource
import app.mvvm.architecture.util.extensions.logAndMapToErrorType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.util.concurrent.TimeUnit

class NewsRepository(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao,
) {
    private val newsRateLimit = RateLimiter<String>(10, TimeUnit.MINUTES)

    fun getNews() = flow {
        val currentNews = newsDao.getAll()
        emit(Resource.Loading(currentNews))
        if (currentNews.isEmpty() || newsRateLimit.shouldFetch(NEWS_RATE_LIMIT_KEY)) {
            newsDao.replaceAll(
                newsApi.getTopHeadlines().newsItems.map { it.toNewsItem() }
            )
        }
        emitAll(newsDao.getAllFlow().map { Resource.Success(it) })
    }.catch { throwable ->
        emitAll(newsDao.getAllFlow().map {
            Resource.Error(throwable.logAndMapToErrorType(), it)
        })
    }.flowOn(Dispatchers.IO)

    fun getNewsItem(id: String) = flow<Resource<NewsItem>> {
        val (author, title) = NewsItem.CompositeKey.fromIdentifier(id)
        emitAll(newsDao.getFlow(author, title).map { Resource.Success(it) })
    }.catch { throwable ->
        emit(Resource.Error(throwable.logAndMapToErrorType(), null))
    }.flowOn(Dispatchers.IO)

    companion object {
        const val NEWS_RATE_LIMIT_KEY = "news"
    }
}