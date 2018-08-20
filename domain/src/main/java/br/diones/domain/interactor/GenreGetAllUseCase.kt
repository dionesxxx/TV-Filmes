package br.diones.domain.interactor

import br.diones.domain.Schedulers
import br.diones.domain.UseCase
import br.diones.domain.entity.Genre
import br.diones.domain.gateway.GenreGateway
import io.reactivex.Observable

class GenreGetAllUseCase(schedulers: Schedulers, private val genreGateway: GenreGateway) :
        UseCase<Void, List<Genre>>(schedulers) {

    override fun buildObservable(params: Void?): Observable<List<Genre>> {
        return genreGateway.getGenres()
    }

}