package com.prad.dagger.app.main

import com.prad.dagger.app.main.country.CurrencyCurrencyCountryProvider
import com.prad.dagger.app.network.NetworkConfiguration
import com.prad.dagger.app.service.CurrencyApiLibrary
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
abstract class MainModule {

    @Binds
    abstract fun providesMainPresenter(mainPresenter: MainPresenter): MainContract.Presenter

    @Binds
    @Singleton
    abstract fun providesCurrencyCountryStore(currencyCountryProvider: CurrencyCurrencyCountryProvider)
            : CurrencyCountryStore

    companion object {

        @Provides
        @JvmStatic
        fun providesCurrencyProvider(library: CurrencyApiLibrary) = library.CurrencyProvider()

        @Provides
        @JvmStatic
        fun providesCurrencyLibrary(retrofit: Retrofit, configuration: NetworkConfiguration) =
            CurrencyApiLibrary(retrofit, configuration)
    }
}
