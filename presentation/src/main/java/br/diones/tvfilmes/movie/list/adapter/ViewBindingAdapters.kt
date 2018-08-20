package br.diones.tvfilmes.movie.list.adapter

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import br.diones.tvfilmes.movie.list.model.MovieModel

object ViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("eventAdapter", "eventCallbacks", requireAll = false)
    fun setEventAdapter(recyclerView: RecyclerView, items: List<MovieModel>, callbacks: MovieListAdapter.Callbacks?) {
        items?.let {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = MovieListAdapter(it, callbacks)
        }
    }
}