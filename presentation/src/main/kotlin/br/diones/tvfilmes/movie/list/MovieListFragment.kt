package br.diones.tvfilmes.movie.list

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v4.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.diones.tvfilmes.R
import br.diones.tvfilmes.databinding.FragmentMovieListBinding
import br.diones.tvfilmes.internal.util.lazyThreadSafetyNone
import br.diones.tvfilmes.movie.list.adapter.MovieListAdapter
import br.diones.tvfilmes.movie.list.model.MovieModel
import br.diones.tvfilmes.navigation.Navigator
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MovieListFragment : DaggerFragment(), MovieListAdapter.Callbacks {

    companion object {

        private const val GENRE_ID = "genre_id"

        fun newInstance(id: String): MovieListFragment {
            val fragment = MovieListFragment()
            val args = Bundle()
            args.putSerializable(GENRE_ID, id)
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var navigator: Navigator

    private lateinit var binder: FragmentMovieListBinding

    private val viewModel by lazyThreadSafetyNone {
        ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)
        binder.viewModel = viewModel
        binder.eventCallbacks = this
        return binder.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val genreMovie = arguments?.getString(GENRE_ID)
        viewModel.loadMovieList(genreMovie)
    }

    override fun onItemClick(view: View, item: MovieModel) {
        val cardView = view.findViewById<View>(R.id.cardview)
        val imageView = view.findViewById<View>(R.id.image_thumbnail)
        val nameView = view.findViewById<View>(R.id.text_name)
        val sharedViews = arrayOf(
                Pair(cardView, ViewCompat.getTransitionName(cardView)),
                Pair(imageView, ViewCompat.getTransitionName(imageView)),
                Pair(nameView, ViewCompat.getTransitionName(nameView)))
        activity?.let { navigator.navigateToMovie(it, item.id, sharedViews) }
    }

}