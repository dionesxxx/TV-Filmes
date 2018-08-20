package br.diones.data.gateway.mapper

import br.diones.data.local.model.MovieLocalModel
import br.diones.domain.entity.Movie

class MovieMapper {

    fun toEntity(movie: MovieLocalModel, genre_id: Int?) =
    Movie(movie.id, genre_id, movie.vote_count, movie.video, movie.vote_average, movie.title, movie.popularity, movie.poster_path,
    movie.original_language, movie.original_title, movie.backdrop_path, movie.adult, movie.overview, movie.release_date)

    fun toEntity(movie: MovieLocalModel) =
            Movie(movie.id, movie.genre_id, movie.vote_count, movie.video, movie.vote_average, movie.title, movie.popularity, movie.poster_path,
                    movie.original_language, movie.original_title, movie.backdrop_path, movie.adult, movie.overview, movie.release_date)

}