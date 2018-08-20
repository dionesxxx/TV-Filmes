package br.diones.data.local.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Genre")
data class GenreLocalModel(
        @PrimaryKey var id: Int,
        var name: String
)