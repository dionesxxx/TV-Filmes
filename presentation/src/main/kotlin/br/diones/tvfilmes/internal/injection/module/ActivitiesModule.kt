package br.diones.tvfilmes.internal.injection.module

import br.diones.tvfilmes.home.HomeActivity
import br.diones.tvfilmes.internal.injection.module.home.HomeModule
import br.diones.tvfilmes.internal.injection.module.movie.MovieDetailModule
import br.diones.tvfilmes.internal.injection.scope.HomeScope
import br.diones.tvfilmes.internal.injection.scope.MovieDetailScope
import br.diones.tvfilmes.movie.detail.MovieDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivitiesModule {

    @HomeScope
    @ContributesAndroidInjector(modules = [HomeModule::class])
    internal abstract fun contributeHomeActivity(): HomeActivity

    @MovieDetailScope
    @ContributesAndroidInjector(modules = [MovieDetailModule::class])
    internal abstract fun contributeMovieDetailActivity(): MovieDetailActivity

}