package br.diones.tvfilmes.movie.list

import android.app.Application
import android.content.Context
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import br.diones.domain.entity.Movie
import br.diones.domain.interactor.MovieGetByGenreUseCase
import br.diones.tvfilmes.R
import br.diones.tvfilmes.internal.util.BaseAndroidViewModel
import br.diones.tvfilmes.movie.list.mapper.MovieMapper
import br.diones.tvfilmes.movie.list.model.MovieModel
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

class MovieListViewModel(context: Context, private val movieGetByGenreUseCase: MovieGetByGenreUseCase)
    : BaseAndroidViewModel(context.applicationContext as Application) {

    private val mapper = MovieMapper(context)

    val loading = ObservableBoolean()
    val result = ObservableArrayList<MovieModel>()
    val error = ObservableField<String>()
    val empty = ObservableBoolean()

    fun loadMovieList(genre: String? = "") {
        addDisposable(findMovieByGenre(genre))
    }

    fun refresh() = loadMovieList()

    private fun findMovieByGenre(genre: String?): Disposable {
        return movieGetByGenreUseCase.execute(genre)
                .subscribeWith(object : DisposableObserver<List<Movie>>() {

                    override fun onStart() {
                        loading.set(true)
                        empty.set(false)
                    }

                    override fun onNext(t: List<Movie>) {
                        loading.set(false)
                        result.clear()
                        result.addAll(t.map { mapper.toModel(it) })
                        empty.set(t.isEmpty())
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