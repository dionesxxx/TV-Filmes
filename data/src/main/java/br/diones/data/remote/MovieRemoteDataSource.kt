package br.diones.data.remote

import br.diones.data.remote.api.MovieService
import br.diones.data.remote.model.MovieRemoteModel
import io.reactivex.Observable

class MovieRemoteDataSource(private val movieService: MovieService) {

    fun getByGenre(genre: String?): Observable<List<MovieRemoteModel>> = movieService.getMovieByGenre(genre)

    fun getById(id: Int?): Observable<MovieRemoteModel> = movieService.getMovieById(id)
}