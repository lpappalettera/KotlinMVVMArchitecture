package app.mvvm.architecture.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {
    @DispatcherDefault
    @Provides
    fun providesDispatcherDefault(): CoroutineDispatcher = Dispatchers.Default

    @DispatcherMain
    @Provides
    fun providesDispatcherMain(): CoroutineDispatcher = Dispatchers.Main

    @DispatcherIO
    @Provides
    fun providesDispatcherIO(): CoroutineDispatcher = Dispatchers.IO
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DispatcherDefault

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DispatcherMain

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DispatcherIO