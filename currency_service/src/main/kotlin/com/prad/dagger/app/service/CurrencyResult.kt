package com.prad.dagger.app.service

sealed class CurrencyResult {

    data class Success(
        val baseCurrency: String,
        val date: String,
        val currencyDetails: List<CurrencyDetails>
    ) : CurrencyResult()

    object Failed : CurrencyResult()

}
