package com.prad.dagger.app.service

import io.reactivex.Observable
import javax.inject.Inject

class CurrencyProvider @Inject constructor(
    private val currencyService: CurrencyService,
    private val currencyDataMapper: CurrencyDataMapper
) {

    fun getCurrencyInfo(): Observable<CurrencyResult> {
        return try {
            currencyService.getCurrency()
                .map { response ->
                    val success = currencyDataMapper.mapFrom(response)
                    success as CurrencyResult
                }
                .onErrorReturn { CurrencyResult.Failed }
        } catch (ex: Exception) {
            Observable.just(CurrencyResult.Failed)
        }

    }
}
