package com.cinema.data.repositortory

import com.example.weather.data.model.MovieDetailsResponceModel
import com.cinema.model.MovieModelResponce
import com.example.weather.data.model.SerialsModelResponce

interface ApiRepository {
    suspend fun geTrending(): MovieModelResponce
    suspend fun getPopularSerials(): SerialsModelResponce
    suspend fun getMovieDetails(id: Long): MovieDetailsResponceModel
    suspend fun getMovieRecommendation(id: Long)
    suspend fun getSerialsDetails(id: Long)
}