package br.diones.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import br.diones.data.local.model.GenreLocalModel
import io.reactivex.Maybe

@Dao
interface GenreDao {

    @Query("SELECT * FROM Genre")
    fun getAll(): Maybe<List<GenreLocalModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg eventTypes: GenreLocalModel)

}