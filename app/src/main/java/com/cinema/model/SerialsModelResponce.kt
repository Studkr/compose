package com.example.weather.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class SerialsModelResponce (
    val page: Long,
    val results: List<SerialResponseModel>,
    @JsonProperty("total_pages")
    val totalPages: Long,
    @JsonProperty("total_results")
    val totalResults: Long
)

data class SerialResponseModel (
    @JsonProperty("backdrop_path")
    val backdropPath: String,
    @JsonProperty("first_air_date")
    val firstAirDate: String,
    @JsonProperty("genre_ids")
    val genreIDS: List<Long>,
    val id: Long,
    val name: String,
    @JsonProperty("origin_country")
    val originCountry: List<String>,
    @JsonProperty("original_language")
    val originalLanguage: String,
    @JsonProperty("original_name")
    val originalName: String,
    val overview: String,
    val popularity: Double,
    @JsonProperty("poster_path")
    val posterPath: String,
    @JsonProperty("vote_average")
    val voteAverage: Double,
    @JsonProperty("vote_count")
    val voteCount: Long
)
