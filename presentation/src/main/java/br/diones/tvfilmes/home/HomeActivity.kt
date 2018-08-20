package br.diones.tvfilmes.home

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import br.diones.tvfilmes.R
import br.diones.tvfilmes.databinding.ActivityHomeBinding
import br.diones.tvfilmes.internal.util.lazyThreadSafetyNone
import br.diones.tvfilmes.movie.genre.GenreViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val binder by lazyThreadSafetyNone<ActivityHomeBinding> {
        DataBindingUtil.setContentView(this, R.layout.activity_home)
    }

    private val viewModel by lazyThreadSafetyNone {
        ViewModelProviders.of(this, viewModelFactory).get(GenreViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binder.toolbar)

        binder.viewModel = viewModel
        binder.fabClick = this

        viewModel.loadGenreList()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                R.id.action_search -> true
                R.id.action_settings -> true
                else -> super.onOptionsItemSelected(item)
            }

    override fun onClick(v: View?) {
        Snackbar.make(binder.root, "Replace with your own action", Snackbar.LENGTH_LONG).show()
    }
}
