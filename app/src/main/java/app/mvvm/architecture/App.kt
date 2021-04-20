package app.mvvm.architecture

import android.app.Application
import app.mvvm.architecture.di.ApiModule
import app.mvvm.architecture.di.DaoModule
import app.mvvm.architecture.di.RepositoryModule
import app.mvvm.architecture.di.ViewModelModule
import app.mvvm.architecture.util.TimberLogger
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            logger(TimberLogger())
            androidContext(this@App)
            modules(
                DaoModule.daoModule,
                ApiModule.apiModule,
                RepositoryModule.repositoryModule,
                ViewModelModule.viewModelModule
            )
        }
    }
}