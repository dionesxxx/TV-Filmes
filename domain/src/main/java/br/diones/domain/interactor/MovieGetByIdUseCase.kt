package br.diones.domain.interactor

import br.diones.domain.Schedulers
import br.diones.domain.UseCase
import br.diones.domain.entity.Movie
import br.diones.domain.gateway.MovieGateway
import io.reactivex.Observable

class MovieGetByIdUseCase(schedulers: Schedulers, private val movieGateway: MovieGateway) :
        UseCase<Int, Movie>(schedulers) {

    override fun buildObservable(params: Int?): Observable<Movie> {
        return movieGateway.getMoviesById(params)
    }

}
