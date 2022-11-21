package com.example.weather.rest

import com.fasterxml.jackson.databind.ObjectMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class JsonModule {

    @Provides
    @Singleton
    fun mapper() = createObjectMapper()

    @Provides
    @Singleton
    fun converterFactory(mapper: ObjectMapper): Converter.Factory = JacksonConverterFactory.create(mapper)
}