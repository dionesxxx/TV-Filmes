package br.diones.data.local

import br.diones.data.local.dao.MovieDao
import br.diones.data.local.model.MovieLocalModel
import io.reactivex.Observable

class MovieLocalDataSource(private val movieDao: MovieDao) {

    fun getByGenre(genre: String?): Observable<List<MovieLocalModel>> = movieDao.getByGenre(genre).toObservable()

    fun insert(movie: MovieLocalModel) = movieDao.insert(movie)

    fun insert(movie: List<MovieLocalModel>) = movieDao.insert(movie)

    fun getById(id: Int?): Observable<MovieLocalModel> = movieDao.getById(id).toObservable()

}