package app.mvvm.architecture.di

import app.mvvm.architecture.repository.NewsRepository
import org.koin.dsl.module

object RepositoryModule {
    val repositoryModule = module {
        factory { NewsRepository(get(), get()) }
    }
}