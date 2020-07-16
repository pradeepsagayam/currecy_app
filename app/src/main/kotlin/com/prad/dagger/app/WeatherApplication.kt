package com.prad.dagger.app

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class CurrencyApplication : Application(), HasAndroidInjector {

    private val appComponent = DaggerApplicationComponent.builder().apply {
        application(this@CurrencyApplication)
    }.build()

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)
    }

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingActivityInjector
}
