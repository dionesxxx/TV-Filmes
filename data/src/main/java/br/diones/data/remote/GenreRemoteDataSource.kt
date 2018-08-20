package br.diones.data.remote

import br.diones.data.remote.api.MovieService
import br.diones.data.remote.model.GenreRemoteModel
import io.reactivex.Observable

class GenreRemoteDataSource(private val movieService: MovieService) {

    fun getAll(): Observable<List<GenreRemoteModel>> = movieService.getGenre()

}