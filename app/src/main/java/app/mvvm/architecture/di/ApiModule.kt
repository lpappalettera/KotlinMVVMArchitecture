package app.mvvm.architecture.di

import app.mvvm.architecture.api.NewsApi
import app.mvvm.architecture.api.interceptor.AuthInterceptor
import app.mvvm.architecture.typeConverter.OffsetDateTimeConverter
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import java.time.OffsetDateTime

object ApiModule {
    private const val API_URL = "https://newsapi.org/v2/"

    val apiModule = module {
        single { provideJsonSerialization() }
        single { provideJsonConverterFactor(get()) }
        factory { AuthInterceptor() }
        factory { provideOkHttpClient(get()) }
        factory { provideNewsApi(get()) }
        single { provideRetrofit(get(), get()) }
    }

    private fun provideJsonSerialization() = Json {
        ignoreUnknownKeys = true
        serializersModule = SerializersModule {
            contextual(OffsetDateTimeConverter.Serializer)
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun provideJsonConverterFactor(json: Json): Converter.Factory {
        return json.asConverterFactory("application/json".toMediaType())
    }

    private fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient()
                .newBuilder()
                .addInterceptor(authInterceptor)
                .build()
    }

    private fun provideRetrofit(
        okHttpClient: OkHttpClient,
        jsonConverterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_URL)
            .client(okHttpClient)
            .addConverterFactory(jsonConverterFactory)
            .build()
    }

    private fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)
}