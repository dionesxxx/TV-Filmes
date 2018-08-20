package br.diones.tvfilmes.movie.list.model

data class MovieModel(
        val id: Int,
        val title: String,
        val overview: String,
        var poster_path: String,
        val vote_average: Double
)