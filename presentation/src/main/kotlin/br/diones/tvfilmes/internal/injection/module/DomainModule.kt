package br.diones.tvfilmes.internal.injection.module

import br.diones.domain.Schedulers
import br.diones.domain.gateway.GenreGateway
import br.diones.domain.gateway.MovieGateway
import br.diones.domain.interactor.GenreGetAllUseCase
import br.diones.domain.interactor.MovieGetByGenreUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class DomainModule {

    @Provides
    @Singleton
    internal fun provideGenreGetAllUseCase(schedulers: Schedulers, genreGateway: GenreGateway): GenreGetAllUseCase {
        return GenreGetAllUseCase(schedulers, genreGateway)
    }

    @Provides
    @Singleton
    internal fun provideMovieGetByGenreUseCase(schedulers: Schedulers, movieGateway: MovieGateway): MovieGetByGenreUseCase {
        return MovieGetByGenreUseCase(schedulers, movieGateway)
    }

}