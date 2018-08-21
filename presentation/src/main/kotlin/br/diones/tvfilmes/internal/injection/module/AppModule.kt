package br.diones.tvfilmes.internal.injection.module

import android.content.Context
import br.diones.domain.Schedulers
import br.diones.tvfilmes.internal.injection.DaggerApplication
import br.diones.tvfilmes.internal.scheduler.AppSchedulers
import br.diones.tvfilmes.navigation.Navigator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: DaggerApplication): Context = application.applicationContext

    @Provides
    @Singleton
    internal fun provideSchedulers(): Schedulers = AppSchedulers()

    @Provides
    @Singleton
    internal fun provideNavigator() = Navigator()

}