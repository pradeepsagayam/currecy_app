package com.prad.dagger.app.service

import io.reactivex.Observable
import retrofit2.http.GET

interface CurrencyService {

    @GET("/latest")
    fun getCurrency(): Observable<CurrencyResponse>
}
