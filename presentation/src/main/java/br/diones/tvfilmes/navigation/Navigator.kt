package br.diones.tvfilmes.navigation

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View
import br.diones.tvfilmes.home.HomeActivity
import br.diones.tvfilmes.movie.detail.MovieDetailActivity

class Navigator {

    companion object {
        private val EXTRA_MOVIE = "${HomeActivity::class.java.`package`.name}.extra.MOVIE"
    }

    fun navigateToHome(activity: Activity) {
        val intent = Intent(activity, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        activity.startActivity(intent)
    }

    fun navigateToMovie(activity: Activity, event: Int, sharedViews: Array<Pair<View, String>>) {
        val intent = Intent(activity, MovieDetailActivity::class.java)
        intent.putExtra(EXTRA_MOVIE, event)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, *sharedViews).toBundle()
        ActivityCompat.startActivity(activity, intent, options)
    }

    fun getMovie(activity: Activity) = activity.intent.getIntExtra(EXTRA_MOVIE, 0)

}