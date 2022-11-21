package com.cinema.data


import com.example.weather.data.model.MovieDetailsResponceModel
import com.cinema.model.MovieModelResponce
import com.example.weather.data.model.SerialsModelResponce
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("/3/trending/movie/week")
    suspend fun getPopularMovies(
    ): MovieModelResponce

    @GET("/3/tv/popular")
    suspend fun getPopularSerials(
    ): SerialsModelResponce

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Long,
    ): MovieDetailsResponceModel

    @GET("/3/movie/{id}/recommendations")
    suspend fun getMovieRecommendation(
        @Path("id") id: Long,
    )

    @GET("/tv/{tv_id}")
    suspend fun getSerialsDetails(
        @Path("tv_id") id: Long
    )
}