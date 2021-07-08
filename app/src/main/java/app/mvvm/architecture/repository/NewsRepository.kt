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

interface NewsRepository {
    fun getNews(): Flow<Resource<List<NewsItem>>>
    fun getNewsItem(id: String): Flow<Resource<NewsItem>>
}

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao,
) : NewsRepository {
    private val newsRateLimit = RateLimiter<String>(10, TimeUnit.MINUTES)

    override fun getNews() = flow {
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

    override fun getNewsItem(id: String) = flow<Resource<NewsItem>> {
        emitAll(newsDao.getFlow(id).map { Resource.Success(it) })
    }.catch { throwable ->
        emit(Resource.Error(throwable.logAndMapToErrorType(), null))
    }.flowOn(Dispatchers.IO)

    companion object {
        const val NEWS_RATE_LIMIT_KEY = "news"
    }
}