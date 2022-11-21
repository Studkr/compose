package com.cinema.data

import com.cinema.BuildConfig


import com.example.weather.rest.JsonModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import javax.inject.Named
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module(includes = [JsonModule::class])
class RetrofitModule {

    @Provides
    @Singleton
    fun retrofit(
        @Named(BuildConfig.URL) baseUrl: String,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ) = createRetrofit(
        baseUrl = baseUrl,
        okHttpClient = okHttpClient,
        converterFactory = converterFactory
    )

    @Provides
    @Singleton
    @Named(BuildConfig.URL)
    fun baseUrl() = BuildConfig.URL


    @Provides
    @Singleton
    fun okHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ) = createOkHttpClient(loggingInterceptor)

    @Provides
    @Singleton
    fun loggingInterceptor() = getLoggingInterceptor()

}