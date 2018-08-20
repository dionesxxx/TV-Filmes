package br.diones.domain.gateway

import br.diones.domain.entity.Movie
import io.reactivex.Observable

interface MovieGateway {

    fun getMoviesByGenre(genre: String?): Observable<List<Movie>>
    fun getMoviesById(id: Int?): Observable<Movie>

}