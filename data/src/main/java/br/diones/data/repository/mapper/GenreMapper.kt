package br.diones.data.repository.mapper

import br.diones.data.local.model.GenreLocalModel
import br.diones.data.remote.model.GenreRemoteModel

class GenreMapper {

    fun toLocal(genre: GenreRemoteModel) = GenreLocalModel(genre.id, genre.name)

    fun toLocal(items: List<GenreRemoteModel>) = items.map { toLocal(it) }

}