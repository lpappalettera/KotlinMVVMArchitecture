package app.mvvm.architecture.di

import app.mvvm.architecture.database.AppDatabase
import app.mvvm.architecture.database.dao.NewsDao
import org.koin.dsl.module

object DaoModule {
    val daoModule = module {
        single { AppDatabase.instance }
        single { getNewsyDao(get()) }
    }

    private fun getNewsyDao(appDatabase: AppDatabase): NewsDao {
        return appDatabase.newsDao()
    }
}