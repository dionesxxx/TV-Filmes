package br.diones.tvfilmes.internal.injection.component

import br.diones.tvfilmes.internal.injection.module.AppModule
import br.diones.tvfilmes.internal.injection.DaggerApplication
import br.diones.tvfilmes.internal.injection.module.ActivitiesModule
import br.diones.tvfilmes.internal.injection.module.DataModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivitiesModule::class,
    AppModule::class,
    DataModule::class])

internal interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DaggerApplication>()
}