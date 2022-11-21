package com.cinema.repository

import com.cinema.model.MovieResponseModel
import com.cinema.repository.enum.ResponseType
import com.example.weather.data.model.SerialResponseModel

data class MediaModel(
    val id: Long,
    val name: String,
    val releaseDate: String,
    val overview: String,
    val poster: String,
    val type:ResponseType
) {
    companion object {

        fun convertFromTrendingMovie(model: List<MovieResponseModel>): List<MediaModel> {
           return model.map { response->
                MediaModel(
                    id = response.id,
                    name = response.title,
                    releaseDate = response.releaseDate,
                    overview = response.overview,
                    poster = response.posterPath,
                    type = ResponseType.MOVIE
                )
            }
        }

        fun convertFromSerialsTrending(model: List<SerialResponseModel>): List<MediaModel> {
            return model.map { response ->
                MediaModel(
                    id = response.id,
                    name = response.name,
                    releaseDate = response.firstAirDate,
                    overview = response.overview,
                    poster = response.posterPath,
                    type = ResponseType.SERIALS
                )
            }
        }
    }
}

