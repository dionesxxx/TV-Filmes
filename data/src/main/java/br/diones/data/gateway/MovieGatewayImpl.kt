package br.diones.data.gateway

import android.util.Log
import br.diones.data.gateway.mapper.MovieMapper
import br.diones.data.repository.MovieRepository
import br.diones.domain.entity.Movie
import br.diones.domain.gateway.MovieGateway
import io.reactivex.Observable

class MovieGatewayImpl(private val movieRepository: MovieRepository) : MovieGateway {
    private val mapper = MovieMapper()

    override fun getMoviesByGenre(genre: String?): Observable<List<Movie>> =
            movieRepository.getByGenre(genre)
                    .doOnError { println("Movie by Genre(${it.message}) Error") }
                    .map { it.map {mapper.toEntity(it, genre?.toInt())  } }

    override fun getMoviesById(id: Int?): Observable<Movie> =
        movieRepository.getById(id)
                .doOnError { println("Movie By Id($id) Error ") }
                .map { mapper.toEntity(it) }

}
