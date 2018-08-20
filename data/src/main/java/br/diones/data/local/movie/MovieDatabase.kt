package br.diones.data.local.movie

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import br.diones.data.local.dao.GenreDao
import br.diones.data.local.dao.MovieDao
import br.diones.data.local.model.GenreLocalModel
import br.diones.data.local.model.MovieLocalModel
import br.diones.data.local.util.Converters

@Database(entities = [GenreLocalModel::class, MovieLocalModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun genreDao(): GenreDao

    abstract fun movieDao(): MovieDao

    companion object {
        fun newInstance(context: Context): MovieDatabase {
            return Room.inMemoryDatabaseBuilder(context, MovieDatabase::class.java).build()
        }
    }
}