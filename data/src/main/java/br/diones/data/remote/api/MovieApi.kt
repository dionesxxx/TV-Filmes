package br.diones.data.remote.api

import br.diones.data.BuildConfig
import br.diones.data.remote.model.GenreRemoteModel
import br.diones.data.remote.model.MovieRemoteModel
import com.serjltt.moshi.adapters.Wrapped
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BASIC
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class MovieApi(baseUrl: String) : MovieService {

    companion object {
        private const val TIMEOUT = 10L
    }

    private val service: MovieService

    init {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if (BuildConfig.DEBUG) BODY else BASIC

        val httpClient = OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(loggingInterceptor)

        val client = httpClient.build()

        val moshi = Moshi.Builder()
                .add(Wrapped.ADAPTER_FACTORY)
                .add(KotlinJsonAdapterFactory())
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

        service = retrofit.create(MovieService::class.java)

    }

    override fun getGenre(): Observable<List<GenreRemoteModel>> = service.getGenre()

    override fun getMovieByGenre(genre: String?): Observable<List<MovieRemoteModel>> = service.getMovieByGenre(genre)

    override fun getMovieById(id: Int?): Observable<MovieRemoteModel> = service.getMovieById(id)


}