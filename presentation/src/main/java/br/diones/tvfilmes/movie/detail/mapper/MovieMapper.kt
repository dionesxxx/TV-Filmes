package br.diones.tvfilmes.movie.detail.mapper

import android.content.Context
import br.diones.domain.entity.Movie
import br.diones.tvfilmes.movie.detail.model.MovieModel

class MovieMapper(private val context: Context) {

    fun toModel(movie: Movie) : MovieModel {
        return MovieModel(movie.id, movie.title, movie.original_title, movie.overview, movie.poster_path, movie.backdrop_path, movie.vote_average)
    }

}