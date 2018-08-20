package br.diones.data.gateway.mapper

import br.diones.data.local.model.GenreLocalModel
import br.diones.domain.entity.Genre

class GenreMapper {

    fun toEntity(genre: GenreLocalModel) = Genre(genre.id, genre.name)

}