package br.diones.domain.interactor

import br.diones.domain.Schedulers
import br.diones.domain.UseCase
import br.diones.domain.entity.Movie
import br.diones.domain.gateway.MovieGateway
import io.reactivex.Observable

class MovieGetByGenreUseCase(schedulers: Schedulers, private val movieGateway: MovieGateway) :
        UseCase<String, List<Movie>>(schedulers) {

    override fun buildObservable(params: String?): Observable<List<Movie>> {
        return movieGateway.getMoviesByGenre(params)
    }

}