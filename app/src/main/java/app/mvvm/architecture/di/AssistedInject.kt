package app.mvvm.architecture.di

import app.mvvm.architecture.ui.newsItem.NewsItemViewModel
import dagger.Module
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent

// Module currently required to support AssistedInject alongside Hilt.
@Module
@InstallIn(ActivityRetainedComponent::class)
interface AssistedInjectModule

// Factory provider entry point to make AssistedInject work alongside Hilt.
@EntryPoint
@InstallIn(ActivityComponent::class)
interface AssistedViewModelFactoryProvider {
    fun newsItemViewModelFactory(): NewsItemViewModel.Factory
}