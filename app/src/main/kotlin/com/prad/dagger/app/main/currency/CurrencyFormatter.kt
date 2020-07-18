package com.prad.dagger.app.main.currency

import javax.inject.Inject

class CurrencyFormatter @Inject constructor() {

    fun getConvertedCurrencyValue(
        baseCurrencyValue: Double,
        currencyValue: Double,
        symbol: String
    ): String {

        return symbol + " " + (currencyValue * baseCurrencyValue).toString()
    }
}
