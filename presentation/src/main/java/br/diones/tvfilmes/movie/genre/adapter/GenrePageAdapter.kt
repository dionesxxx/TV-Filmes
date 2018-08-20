package br.diones.tvfilmes.movie.genre.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import br.diones.domain.entity.Genre
import br.diones.tvfilmes.movie.list.MovieListFragment

class GenrePageAdapter(fm: FragmentManager, private val items: List<Genre>) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return MovieListFragment.newInstance(items[position].id.toString())
    }

    override fun getCount() = items.size

    override fun getPageTitle(position: Int) = items[position].name

}