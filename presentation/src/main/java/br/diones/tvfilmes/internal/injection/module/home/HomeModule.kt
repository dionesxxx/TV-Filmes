package br.diones.tvfilmes.internal.injection.module.home

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import br.diones.domain.Schedulers
import br.diones.domain.gateway.GenreGateway
import br.diones.domain.gateway.MovieGateway
import br.diones.domain.interactor.GenreGetAllUseCase
import br.diones.domain.interactor.MovieGetByGenreUseCase
import br.diones.tvfilmes.internal.injection.scope.HomeScope
import br.diones.tvfilmes.movie.genre.GenreViewModel
import br.diones.tvfilmes.movie.list.MovieListFragment
import br.diones.tvfilmes.movie.list.MovieListViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class HomeModule {

    @ContributesAndroidInjector
    internal abstract fun  contributeMovieListFragment(): MovieListFragment

    @Module
    companion object {

        @HomeScope
        @Provides
        @JvmStatic
        internal fun provideGenreGetAllUseCase(schedulers: Schedulers, genreGateway: GenreGateway) : GenreGetAllUseCase {
            return GenreGetAllUseCase(schedulers, genreGateway)
        }

        @HomeScope
        @Provides
        @JvmStatic
        internal fun provideMovieGetByGenreUseCase(schedulers: Schedulers, movieGateway: MovieGateway): MovieGetByGenreUseCase {
            return MovieGetByGenreUseCase(schedulers, movieGateway)
        }

        @HomeScope
        @Provides
        @JvmStatic
        internal fun provideViewModelFactory(context: Context,
                                             genreGetAllUseCase: GenreGetAllUseCase,
                                             movieGetByGenreUseCase: MovieGetByGenreUseCase): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return when {
                        modelClass.isAssignableFrom(GenreViewModel::class.java) ->
                                GenreViewModel(context, genreGetAllUseCase) as T

                        modelClass.isAssignableFrom(MovieListViewModel::class.java) ->
                            MovieListViewModel(context, movieGetByGenreUseCase) as T

                        else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                    }
                }
            }
        }
    }

}