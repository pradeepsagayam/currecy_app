package com.prad.dagger.app

import com.prad.dagger.app.main.MainActivity
import com.prad.dagger.app.main.MainModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityInjector::class,

        AppConfigModule::class,

        MainModule::class
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(CurrencyApplication: CurrencyApplication): Builder

        fun build(): ApplicationComponent
    }

    fun inject(CurrencyApplication: CurrencyApplication)

    fun inject(mainActivity: MainActivity)
}
