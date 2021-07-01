package app.mvvm.architecture.di

import app.mvvm.architecture.api.NewsApi
import app.mvvm.architecture.database.dao.NewsDao
import app.mvvm.architecture.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    fun provideNewsRepository(newsApi: NewsApi, newsDao: NewsDao): NewsRepository {
        return NewsRepository(newsApi, newsDao)
    }
}