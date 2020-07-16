package com.prad.dagger.app.service

import com.prad.dagger.app.network.NetworkConfiguration
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [CurrencyApiModule::class])
interface CurrencyApiComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun retrofit(retrofit: Retrofit): Builder

        @BindsInstance
        fun networkConfiguration(networkConfiguration: NetworkConfiguration): Builder

        fun build(): CurrencyApiComponent
    }

    fun CurrencyProvider(): CurrencyProvider
}
