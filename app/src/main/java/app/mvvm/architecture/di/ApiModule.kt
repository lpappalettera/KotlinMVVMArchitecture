package app.mvvm.architecture.di

import app.mvvm.architecture.api.NewsApi
import app.mvvm.architecture.api.interceptor.AuthInterceptor
import app.mvvm.architecture.typeConverter.OffsetDateTimeConverter
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiModule {
    private const val API_URL = "https://newsapi.org/v2/"

    val apiModule = module {
        single {
            Moshi.Builder()
                    .add(OffsetDateTimeConverter)
                    .build()
        }
        single { MoshiConverterFactory.create(get()) }
        factory { AuthInterceptor() }
        factory { provideOkHttpClient(get()) }
        factory { provideNewsApi(get()) }
        single { provideRetrofit(get(), get()) }
    }

    private fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)

    private fun provideRetrofit(
            okHttpClient: OkHttpClient,
            moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl(API_URL)
                .client(okHttpClient)
                .addConverterFactory(moshiConverterFactory)
                .build()
    }

    private fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient()
                .newBuilder()
                .addInterceptor(authInterceptor)
                .build()
    }
}