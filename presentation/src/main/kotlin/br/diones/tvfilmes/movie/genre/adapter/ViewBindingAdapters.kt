package br.diones.tvfilmes.movie.genre.adapter

import android.databinding.BindingAdapter
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import br.diones.domain.entity.Genre

object ViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("eventTypeAdapter")
    fun setGenreAdapter(viewPager: ViewPager, items: List<Genre>?) {
        items?.let {
            val fm = (viewPager.context as FragmentActivity).supportFragmentManager
            viewPager.adapter = GenrePageAdapter(fm, it)
        }
    }

}