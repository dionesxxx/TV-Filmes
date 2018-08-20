package br.diones.tvfilmes.movie.list.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.diones.tvfilmes.R
import br.diones.tvfilmes.databinding.FragmentMovieListBinding
import br.diones.tvfilmes.databinding.FragmentMovieListItemBinding
import br.diones.tvfilmes.movie.list.model.MovieModel

class MovieListAdapter(private val items: List<MovieModel>, private val callbacks: Callbacks? = null) :
        RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    interface Callbacks {
        fun onItemClick(view: View, item: MovieModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val binding: FragmentMovieListItemBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.movie = items[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val binding: FragmentMovieListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener { callbacks?.onItemClick(it, items[adapterPosition]) }
        }
    }
}