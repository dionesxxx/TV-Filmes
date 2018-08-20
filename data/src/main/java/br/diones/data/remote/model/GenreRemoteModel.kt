package br.diones.data.remote.model

import com.squareup.moshi.Json

data class GenreRemoteModel(
        @Json(name = "id") var id: Int,
        @Json(name = "name") var name: String
)