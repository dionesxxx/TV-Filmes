package br.diones.data.remote.model

import com.squareup.moshi.Json

data class MovieRemoteModel(
        @Json(name = "vote_count") var vote_count: Int,
        @Json(name = "id") var id: Int,
        @Json(name = "video") var video: Boolean,
        @Json(name = "vote_average") var vote_average: Double,
        @Json(name = "title") var title: String,
        @Json(name = "popularity") var popularity: Double,
        @Json(name = "poster_path") var poster_path: String,
        @Json(name = "original_language") var original_language: String,
        @Json(name = "original_title") var original_title: String,
        @Json(name = "genre_ids") var genre_id: List<Int>,
        @Json(name = "backdrop_path") var backdrop_path: String,
        @Json(name = "adult") var adult: Boolean,
        @Json(name = "overview") var overview: String,
        @Json(name = "release_date") var release_date: String
)