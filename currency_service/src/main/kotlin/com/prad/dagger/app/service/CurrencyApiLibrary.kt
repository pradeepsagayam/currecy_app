package com.prad.dagger.app.service

import com.prad.dagger.app.network.NetworkConfiguration
import retrofit2.Retrofit

class CurrencyApiLibrary(
    private val retrofit: Retrofit,
    private val networkConfiguration: NetworkConfiguration
) {

    private val component = DaggerCurrencyApiComponent.builder().apply {
        retrofit(retrofit)
        networkConfiguration(networkConfiguration)
    }.build()

    fun CurrencyProvider(): CurrencyProvider = component.CurrencyProvider()
}
