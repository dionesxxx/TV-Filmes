package br.diones.tvfilmes.movie.detail

import android.app.Application
import android.content.Context
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import br.diones.domain.entity.Movie
import br.diones.domain.interactor.MovieGetByIdUseCase
import br.diones.tvfilmes.R
import br.diones.tvfilmes.internal.util.BaseAndroidViewModel
import br.diones.tvfilmes.movie.detail.mapper.MovieMapper
import br.diones.tvfilmes.movie.detail.model.MovieModel
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

class MovieDetailViewModel(context: Context, private val movieGetByGenreUseCase: MovieGetByIdUseCase)
    : BaseAndroidViewModel(context.applicationContext as Application) {

    private val mapper = MovieMapper(context)

    val loading = ObservableBoolean()
    val movie = ObservableField<MovieModel>()
    val error = ObservableField<String>()
    val empty = ObservableBoolean()

    fun loadMovieDetail(id: Int) = addDisposable(getMovieById(id))

    private fun getMovieById(id: Int): Disposable {
        return movieGetByGenreUseCase.execute(id)
                .subscribeWith(object : DisposableObserver<Movie>() {

                    override fun onStart() {
                        loading.set(true)
                    }

                    override fun onNext(result: Movie) {
                        loading.set(false)
                        movie.set(mapper.toModel(result))
                    }

                    override fun onError(t: Throwable) {
                        loading.set(false)
                        error.set(t.localizedMessage ?: t.message ?: context.getString(R.string.unknown_error))
                    }

                    override fun onComplete() {

                    }
                })

    }

}