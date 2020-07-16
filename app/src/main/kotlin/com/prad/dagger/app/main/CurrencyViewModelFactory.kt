package com.prad.dagger.app.main

import com.prad.dagger.app.main.list.CurrencyListViewModel
import com.prad.dagger.app.main.list.viewmodels.CurrencyListCurrencyViewModel
import com.prad.dagger.app.service.CurrencyDetails
import com.prad.dagger.app.service.CurrencyResult.Success
import java.math.BigDecimal
import javax.inject.Inject

class CurrencyViewModelFactory @Inject constructor(
    private val currencyCountryStore: CurrencyCountryStore
) {

    fun generateViewModels(success: Success): List<CurrencyListViewModel> {

        val baseCurrencyDetails = CurrencyDetails(success.baseCurrency, 1.toDouble())

        return success.currencyDetails
            .map {
                val country = currencyCountryStore.getCountry(it.code)

                val convertedAmount = getConvertedValue(
                    baseCurrencyDetails.value,
                    it.value,
                    country!!.symbol
                )

                CurrencyListCurrencyViewModel(
                    it.code,
                    convertedAmount,
                    country.name,
                    getFromCurrency(baseCurrencyDetails, it)
                )
            }
    }

    private fun getConvertedValue(
        baseCurrencyValue: Double,
        currencyValue: Double,
        symbol: String
    ): String {

        return symbol + " " + (currencyValue * baseCurrencyValue).toString()
    }

    private fun getFromCurrency(
        baseCurrencyDetails: CurrencyDetails?,
        currencyDetails: CurrencyDetails
    ): String {
        val format = "%s %s = %s %s"
        val divident = baseCurrencyDetails!!.value / currencyDetails.value
        var bd = BigDecimal(divident)
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP)

        return String.format(
            format,
            "1",
            currencyDetails.code,
            bd.toString(),
            baseCurrencyDetails.code
        )
    }
}
