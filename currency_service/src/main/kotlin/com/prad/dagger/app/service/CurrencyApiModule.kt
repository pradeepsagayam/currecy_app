package com.prad.dagger.app.service

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CurrencyApiModule {

    @Provides
    fun providesCurrencyService(retrofit: Retrofit): CurrencyService =
        retrofit.create(CurrencyService::class.java)
}
