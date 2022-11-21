package com.cinema.data.repositortory

import com.cinema.data.ApiService
import com.example.weather.data.model.MovieDetailsResponceModel
import com.cinema.model.MovieModelResponce
import com.example.weather.data.model.SerialsModelResponce
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : ApiRepository {

    override suspend fun geTrending(): MovieModelResponce = apiService.getPopularMovies()

    override suspend fun getPopularSerials(): SerialsModelResponce = apiService.getPopularSerials()

    override suspend fun getMovieDetails(id: Long): MovieDetailsResponceModel =
        apiService.getMovieDetails(id)

    override suspend fun getMovieRecommendation(id: Long) {

    }

    override suspend fun getSerialsDetails(id: Long) {

    }
}