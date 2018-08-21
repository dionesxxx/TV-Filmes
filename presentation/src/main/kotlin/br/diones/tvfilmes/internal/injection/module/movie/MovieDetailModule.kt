package br.diones.tvfilmes.internal.injection.module.movie

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import br.diones.domain.Schedulers
import br.diones.domain.gateway.MovieGateway
import br.diones.domain.interactor.MovieGetByIdUseCase
import br.diones.tvfilmes.internal.injection.scope.MovieDetailScope
import br.diones.tvfilmes.movie.detail.MovieDetailViewModel
import br.diones.tvfilmes.movie.list.MovieListViewModel
import dagger.Module
import dagger.Provides

@Module
internal abstract class MovieDetailModule {

    @Module
    companion object {

        @MovieDetailScope
        @Provides
        @JvmStatic
        internal fun provideMovieGetByIdUseCase(schedulers: Schedulers, movieGateway: MovieGateway): MovieGetByIdUseCase {
            return MovieGetByIdUseCase(schedulers, movieGateway)
        }

        @MovieDetailScope
        @Provides
        @JvmStatic
        internal fun provideViewModelFactory(context: Context,
                                             movieGetByIdUseCase: MovieGetByIdUseCase): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return when {
                        modelClass.isAssignableFrom(MovieDetailViewModel::class.java) ->
                            MovieDetailViewModel(context, movieGetByIdUseCase) as T

                        else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                    }
                }
            }
        }
    }

}
