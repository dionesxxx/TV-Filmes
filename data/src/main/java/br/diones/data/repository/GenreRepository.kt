package br.diones.data.repository

import br.diones.data.local.GenreLocalDataSource
import br.diones.data.local.model.GenreLocalModel
import br.diones.data.local.model.MovieLocalModel
import br.diones.data.remote.GenreRemoteDataSource
import br.diones.data.repository.mapper.GenreMapper
import io.reactivex.Observable

class GenreRepository(
        private val genreLocalDataSource: GenreLocalDataSource,
        private val genreRemoteDataSource: GenreRemoteDataSource,
        private val genreMapper: GenreMapper) {

    fun getAll(): Observable<List<GenreLocalModel>> {

        val local = genreLocalDataSource.getAll()
                .filter { !it.isEmpty() }
        val remote = genreRemoteDataSource.getAll()
                .map { genreMapper.toLocal(it) }
                .doOnNext { genreLocalDataSource.insertAll(it) }

        return Observable.concat(local, remote)
                .firstElement()
                .toObservable()

    }

}