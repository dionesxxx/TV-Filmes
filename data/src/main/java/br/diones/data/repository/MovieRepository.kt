package br.diones.data.repository

import br.diones.data.local.MovieLocalDataSource
import br.diones.data.local.model.MovieLocalModel
import br.diones.data.remote.MovieRemoteDataSource
import br.diones.data.repository.mapper.MovieMapper
import io.reactivex.Observable

class MovieRepository(
        private val movieLocalDataSource: MovieLocalDataSource,
        private val movieRemoteDataSource: MovieRemoteDataSource,
        private val movieMapper: MovieMapper) {

    fun getByGenre(genre: String?): Observable<List<MovieLocalModel>> {

        val local = movieLocalDataSource.getByGenre(genre)
                .filter { !it.isEmpty() }

        val remote = movieRemoteDataSource.getByGenre(genre)
                .map { movieMapper.toLocal(it) }
                .doOnNext { movieLocalDataSource.insert(it) }

        return Observable.concat(local, remote)
                .firstElement()
                .toObservable()
    }

    fun getById(id: Int?): Observable<MovieLocalModel> {
        val local = movieLocalDataSource.getById(id)

        val remoto = movieRemoteDataSource.getById(id)
                .map { movieMapper.toLocal(it) }
                .doOnNext { movieLocalDataSource.insert(it) }

        return Observable.concat(local, remoto)
                .firstElement()
                .toObservable()
    }

}