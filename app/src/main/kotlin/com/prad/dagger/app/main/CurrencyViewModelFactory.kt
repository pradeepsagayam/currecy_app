package com.prad.dagger.app.main

import com.prad.dagger.app.main.list.CurrencyListViewModel
import com.prad.dagger.app.main.list.viewmodels.CurrencyListCurrencyViewModel
import com.prad.dagger.app.service.CurrencyDetails
import com.prad.dagger.app.service.CurrencyResult.Success
import javax.inject.Inject

class CurrencyViewModelFactory @Inject constructor(
    private val currencyCountryStore: CurrencyCountryStore
) {

    fun generateViewModels(success: Success): List<CurrencyListViewModel> {

        val baseCurrencyDetails = CurrencyDetails(success.baseCurrency, 1.toDouble())

        return success.currencyDetails
            .map {
                val country = currencyCountryStore.getCountry(it.code)

                CurrencyListCurrencyViewModel(
                    it.code,
                    getConvertedValue(baseCurrencyDetails!!.value, it.value),
                    country!!.name,
                    getFromCurrency(baseCurrencyDetails, it)
                )
            }
    }

    private fun getConvertedValue(baseCurrencyValue: Double, currencyValue: Double): String {

        return (currencyValue * baseCurrencyValue).toString()
    }

    private fun getFromCurrency(
        baseCurrencyDetails: CurrencyDetails?,
        currencyDetails: CurrencyDetails
    ): String {
        val format = "%s %s = %s %s"

        return String.format(
            format,
            currencyDetails.value.toString(),
            currencyDetails.code,
            baseCurrencyDetails!!.value.toString(),
            baseCurrencyDetails.code
        )
    }
}
