package com.prad.dagger.app

import com.prad.dagger.app.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityInjector {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity
}
