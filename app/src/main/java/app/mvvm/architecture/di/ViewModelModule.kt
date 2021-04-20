package app.mvvm.architecture.di

import app.mvvm.architecture.ui.newsOverview.NewsOverviewViewModel
import org.koin.dsl.module

object ViewModelModule {
    val viewModelModule = module {
        factory { NewsOverviewViewModel(get()) }
    }
}