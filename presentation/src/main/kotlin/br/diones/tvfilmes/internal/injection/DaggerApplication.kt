package br.diones.tvfilmes.internal.injection

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import br.diones.tvfilmes.internal.injection.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class DaggerApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = activityInjector
    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> = fragmentInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().create(this).inject(this);
    }
}