package br.diones.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import br.diones.data.local.model.MovieLocalModel
import io.reactivex.Maybe

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie WHERE genre_id = :genre")
    fun getByGenre(genre: String?): Maybe<List<MovieLocalModel>>

    @Query("SELECT * FROM Movie WHERE id = :id")
    fun getById(id: Int?): Maybe<MovieLocalModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(event: MovieLocalModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: List<MovieLocalModel>)

}