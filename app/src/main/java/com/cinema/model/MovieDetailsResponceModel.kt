package com.example.weather.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class MovieDetailsResponceModel(
    val adult: Boolean,

    @JsonProperty("backdrop_path")
    val backdropPath: String,

    @JsonProperty("belongs_to_collection")
    val belongsToCollection: Any? = null,

    val budget: Long,
    val genres: List<Genre>,
    val homepage: String,
    val id: Long,

    @JsonProperty("imdb_id")
    val imdbID: String,

    @JsonProperty("original_language")
    val originalLanguage: String,

    @JsonProperty("original_title")
    val originalTitle: String,

    val overview: String,
    val popularity: Double,

    @JsonProperty("poster_path")
    val posterPath: String,

    @JsonProperty("production_companies")
    val productionCompanies: List<ProductionCompany>,

    @JsonProperty("production_countries")
    val productionCountries: List<ProductionCountry>,

    @JsonProperty("release_date")
    val releaseDate: String,

    val revenue: Long,
    val runtime: Long,

    @JsonProperty("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,

    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,

    @JsonProperty("vote_average")
    val voteAverage: Double,

    @JsonProperty("vote_count")
    val voteCount: Long
)


data class Genre(
    val id: Long,
    val name: String
)


data class ProductionCompany(
    val id: Long,

    @JsonProperty("logo_path")
    val logoPath: String?,

    val name: String,

    @JsonProperty("origin_country")
    val originCountry: String
)


data class ProductionCountry(
    @JsonProperty("iso_3166_1")
    val iso3166_1: String,

    val name: String
)

data class SpokenLanguage(
    @JsonProperty("english_name")
    val englishName: String,

    @JsonProperty("iso_639_1")
    val iso639_1: String,

    val name: String
)
