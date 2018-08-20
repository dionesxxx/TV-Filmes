package br.diones.tvfilmes.movie.detail

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.ViewGroup
import androidx.view.doOnPreDraw
import br.diones.tvfilmes.R
import br.diones.tvfilmes.databinding.ActivityMovieDetailBinding
import br.diones.tvfilmes.internal.util.lazyThreadSafetyNone
import br.diones.tvfilmes.navigation.Navigator
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MovieDetailActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var navigator: Navigator

    private val binder by lazyThreadSafetyNone<ActivityMovieDetailBinding> {
        DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)
    }

    private val movieDetailViewModel by lazyThreadSafetyNone {
        ViewModelProviders.of(this, viewModelFactory).get(MovieDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportPostponeEnterTransition()

        setSupportActionBar (binder.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binder.movieDetailViewModel = movieDetailViewModel

        val movie = navigator.getMovie(this)
        movieDetailViewModel.loadMovieDetail(movie)

        movieDetailViewModel.movie.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable, propertyId: Int) {
                (window.decorView as ViewGroup).doOnPreDraw {
                    supportStartPostponedEnterTransition()
                }
            }
        })
    }

    override fun getParentActivityIntent(): Intent {
        return super.getParentActivityIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_movie_detail, menu)
        return true
    }
}
