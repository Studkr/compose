package com.cinema.model

import com.fasterxml.jackson.annotation.JsonProperty


data class MovieModelResponce (
    val page: Long,
    val results: List<MovieResponseModel>,
    val totalPages: Long,
    val totalResults: Long
)

data class MovieResponseModel (
    val video: Boolean,
    @JsonProperty("vote_average")
    val voteAverage: Double,
    val title: String,
    val overview: String,
    @JsonProperty("release_date")
    val releaseDate: String,
    val adult: Boolean,
    @JsonProperty("backdrop_path")
    val backdropPath: String,
    val id: Long,
    @JsonProperty("genre_ids")
    val genreIDS: List<Long>,
    @JsonProperty("vote_count")
    val voteCount: Long,
    @JsonProperty("original_language")
    val originalLanguage: String,
    @JsonProperty("original_title")
    val originalTitle: String,
    @JsonProperty("poster_path")
    val posterPath: String,
    val popularity: Double,
    @JsonProperty("media_type")
    val mediaType: String
)


