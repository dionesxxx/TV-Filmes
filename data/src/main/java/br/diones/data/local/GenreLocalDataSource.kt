package br.diones.data.local

import br.diones.data.local.dao.GenreDao
import br.diones.data.local.model.GenreLocalModel
import io.reactivex.Observable

class GenreLocalDataSource(private val genreDao: GenreDao) {

    fun getAll(): Observable<List<GenreLocalModel>> = genreDao.getAll().toObservable()

    fun insertAll(genres: List<GenreLocalModel>) = genreDao.insertAll(*genres.toTypedArray())

}