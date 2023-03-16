package com.openapi.task.di

import com.google.gson.Gson
import com.openapi.task.BuildConfig
import com.openapi.task.data.remote.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providesUrl() = BuildConfig.ROOT_API

    @Provides
    @Singleton
    fun apiService(url: String): ApiServices {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHtttpClient())
            .build()
            .create(ApiServices::class.java)
    }
    var time: Long = 1
    @Provides
    @Singleton
    fun provideOkHtttpClient(): OkHttpClient {
        var okHttpClient: OkHttpClient.Builder =
            OkHttpClient.Builder()
                .addInterceptor(object : Interceptor {
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val request =
                            chain.request().newBuilder().build()
                        return chain.proceed(request)
                    }

                })
        okHttpClient.readTimeout(time, TimeUnit.MINUTES)
        okHttpClient.writeTimeout(time, TimeUnit.MINUTES)
        var client: OkHttpClient = okHttpClient.build()
        return client
    }
}