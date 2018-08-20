package br.diones.data.gateway

import android.util.Log
import br.diones.data.gateway.mapper.GenreMapper
import br.diones.data.repository.GenreRepository
import br.diones.domain.entity.Genre
import br.diones.domain.gateway.GenreGateway
import io.reactivex.Observable

class GenreGatewayImpl(private val genreRepository: GenreRepository) : GenreGateway {

    private val mapper = GenreMapper()

    override fun getGenres(): Observable<List<Genre>> =
            genreRepository.getAll()
                    .doOnError { println("Genre Type Error") }
                    .map { it.map {
                        Log.d("GENRE", it.toString())
                        mapper.toEntity(it)
                    } }

}