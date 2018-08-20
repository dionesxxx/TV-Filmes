package br.diones.data.remote.api

import br.diones.data.BuildConfig
import br.diones.data.remote.model.GenreRemoteModel
import br.diones.data.remote.model.MovieRemoteModel
import com.serjltt.moshi.adapters.Wrapped
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    companion object {
        private const val APIKEY = BuildConfig.API_KEY
    }

    @GET("genre/movie/list?api_key=$APIKEY")
    @Wrapped(path = ["genres"])
    fun getGenre(): Observable<List<GenreRemoteModel>>

    @GET("discover/movie?api_key=$APIKEY")
    @Wrapped(path = ["results"])
    fun getMovieByGenre(@Query("with_genres") genre: String?): Observable<List<MovieRemoteModel>>

    @GET("/movie/{movie_id}")
    fun getMovieById(@Path("movie_id") id: Int?): Observable<MovieRemoteModel>

}