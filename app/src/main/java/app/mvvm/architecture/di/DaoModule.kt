package app.mvvm.architecture.di

import app.mvvm.architecture.database.AppDatabase
import app.mvvm.architecture.database.dao.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun provideAppDatabase(): AppDatabase = AppDatabase.instance

    @Provides
    fun provideNewsyDao(appDatabase: AppDatabase): NewsDao {
        return appDatabase.newsDao()
    }
}