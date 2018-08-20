package br.diones.tvfilmes.movie.detail.model

data class MovieModel(
        val id: Int,
        val title: String,
        val original_title: String,
        val overview: String,
        var poster_path: String,
        val backdrop_path: String,
        val vote_average: Double
)