package br.diones.data.local.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Movie")
data class MovieLocalModel(
        @PrimaryKey var id: Int,
        val genre_id: Int,
        val vote_count: Int,
        val video: Boolean,
        val vote_average: Double,
        val title: String,
        val popularity: Double,
        val poster_path: String,
        val original_language: String,
        val original_title: String,
        val backdrop_path: String,
        val adult: Boolean,
        val overview: String,
        val release_date: String)