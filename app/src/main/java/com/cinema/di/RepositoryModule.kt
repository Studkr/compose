package com.cinema.di

import com.cinema.data.repositortory.ApiRepository
import com.cinema.data.repositortory.ApiRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideApiRepository(impl:ApiRepositoryImpl):ApiRepository = impl
}