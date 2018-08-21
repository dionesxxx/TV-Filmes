package br.diones.domain.gateway

import br.diones.domain.entity.Genre
import io.reactivex.Observable

interface GenreGateway {
    fun getGenres(): Observable<List<Genre>>
}