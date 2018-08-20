package br.diones.data.repository.mapper

import br.diones.data.local.model.MovieLocalModel
import br.diones.data.remote.model.MovieRemoteModel

class MovieMapper {

    fun toLocal(movie: MovieRemoteModel, genre_id: Int = 0) =
            MovieLocalModel(movie.id, genre_id, movie.vote_count, movie.video, movie.vote_average, movie.title, movie.popularity, movie.poster_path,
                    movie.original_language, movie.original_title, movie.backdrop_path, movie.adult, movie.overview, movie.release_date)

    fun toLocal(items: List<MovieRemoteModel>) = items.map { toLocal(it) }

}