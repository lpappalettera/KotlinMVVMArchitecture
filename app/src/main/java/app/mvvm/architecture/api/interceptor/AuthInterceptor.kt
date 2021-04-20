package app.mvvm.architecture.api.interceptor

import app.mvvm.architecture.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("X-Api-Key", BuildConfig.NEWS_API_KEY)
            .build()
        return chain.proceed(newRequest)
    }
}