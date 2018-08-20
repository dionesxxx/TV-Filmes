package br.diones.tvfilmes.movie.genre

import android.app.Application
import android.content.Context
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import br.diones.domain.entity.Genre
import br.diones.domain.interactor.GenreGetAllUseCase
import br.diones.tvfilmes.R
import br.diones.tvfilmes.internal.util.BaseAndroidViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

class GenreViewModel(context: Context, private val genreGetAllUseCase: GenreGetAllUseCase) :
BaseAndroidViewModel(context.applicationContext as Application) {

    val loading = ObservableBoolean()
    val result = ObservableArrayList<Genre>()
    val empty = ObservableBoolean()
    val error = ObservableField<String>()

    fun loadGenreList() = addDisposable(getAllGenre())

    private fun getAllGenre(): Disposable {
        return genreGetAllUseCase.execute()
                .subscribeWith(object : DisposableObserver<List<Genre>>(){
                    override fun onStart() {
                        loading.set(true)
                    }

                    override fun onNext(t: List<Genre>) {
                        loading.set(false)
                        result.clear()
                        result.addAll(t)
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