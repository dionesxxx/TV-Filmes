package br.diones.tvfilmes.internal.injection.module

import android.content.Context
import br.diones.data.BuildConfig
import br.diones.data.gateway.GenreGatewayImpl
import br.diones.data.gateway.MovieGatewayImpl
import br.diones.data.local.GenreLocalDataSource
import br.diones.data.local.MovieLocalDataSource
import br.diones.data.local.dao.GenreDao
import br.diones.data.local.dao.MovieDao
import br.diones.data.local.movie.MovieDatabase
import br.diones.data.remote.GenreRemoteDataSource
import br.diones.data.remote.MovieRemoteDataSource
import br.diones.data.remote.api.MovieApi
import br.diones.data.remote.api.MovieService
import br.diones.data.repository.GenreRepository
import br.diones.data.repository.MovieRepository
import br.diones.data.repository.mapper.GenreMapper
import br.diones.data.repository.mapper.MovieMapper
import br.diones.domain.gateway.GenreGateway
import br.diones.domain.gateway.MovieGateway
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class DataModule {

    @Provides
    @Singleton
    internal fun provideMovieService(): MovieService = MovieApi(BuildConfig.API_URL)

    @Provides
    @Singleton
    internal fun provideGenreRemoteDataSource(movieService: MovieService): GenreRemoteDataSource {
        return GenreRemoteDataSource(movieService)
    }

    @Provides
    @Singleton
    internal fun provideMovieRemoteDataSource(movieService: MovieService): MovieRemoteDataSource {
        return MovieRemoteDataSource(movieService)
    }

    @Provides
    @Singleton
    internal fun provideMovieDatabase(context: Context): MovieDatabase {
        return MovieDatabase.newInstance(context)
    }

    @Provides
    @Singleton
    internal fun provideGenreDao(movieDatabase: MovieDatabase): GenreDao = movieDatabase.genreDao()

    @Provides
    @Singleton
    internal fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao = movieDatabase.movieDao()

    @Provides
    @Singleton
    internal fun provideGenreDiskDataSource(genreDao: GenreDao): GenreLocalDataSource {
        return GenreLocalDataSource(genreDao)
    }

    @Provides
    @Singleton
    internal fun provideMovieDiskDataSource(movieDao: MovieDao): MovieLocalDataSource {
        return MovieLocalDataSource(movieDao)
    }

    @Provides
    @Singleton
    internal fun provideGenreRepository(genreLocalDataSource: GenreLocalDataSource,
                                        genreRemoteDataSource: GenreRemoteDataSource): GenreRepository {
        return GenreRepository(genreLocalDataSource, genreRemoteDataSource, GenreMapper())
    }

    @Provides
    @Singleton
    internal fun provideMovieRepository(movieLocalDataSource: MovieLocalDataSource,
                                        movieRemoteDataSource: MovieRemoteDataSource): MovieRepository {
        return MovieRepository(movieLocalDataSource, movieRemoteDataSource, MovieMapper())
    }

    @Provides
    @Singleton
    internal fun provideGenreGateway(genreRepository: GenreRepository): GenreGateway {
        return GenreGatewayImpl(genreRepository)
    }

    @Provides
    @Singleton
    internal fun provideMovieGateway(movieRepository: MovieRepository): MovieGateway {
        return MovieGatewayImpl(movieRepository)
    }
}