package br.diones.domain.entity

data class Movie(
        val id: Int,
        val genre_id: Int? = 0,
        val vote_count: Int,
        val video: Boolean,
        val vote_average: Double,
        val title: String,
        val popularity: Double,
        val poster_path: String,
        val original_language: String,
        val original_title: String,
        val backdrop_path: String,
        val adult: Boolean,
        val overview: String,
        val release_date: String
)