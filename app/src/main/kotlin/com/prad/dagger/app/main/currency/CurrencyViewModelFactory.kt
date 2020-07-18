package com.prad.dagger.app.main.currency

import com.prad.dagger.app.main.CurrencyCountryStore
import com.prad.dagger.app.main.currency.list.CurrencyListViewModel
import com.prad.dagger.app.main.currency.list.viewmodels.CurrencyListCurrencyViewModel
import com.prad.dagger.app.service.CurrencyDetails
import com.prad.dagger.app.service.CurrencyResult.Success
import javax.inject.Inject

class CurrencyViewModelFactory @Inject constructor(
    private val currencyCountryStore: CurrencyCountryStore,
    private val currencyFormatter: CurrencyFormatter
) {

    fun generateViewModels(success: Success): List<CurrencyListViewModel> {

        val baseCurrencyDetails = CurrencyDetails(success.baseCurrency, 1.0)

        val viewModels = success.currencyDetails
            .map {
                currencyListCurrencyViewModel(it, baseCurrencyDetails)
            }.toMutableList()

        viewModels.add(
            0,
            currencyListCurrencyViewModel(
                baseCurrencyDetails,
                baseCurrencyDetails
            )
        )

        return viewModels
    }

    private fun currencyListCurrencyViewModel(
        it: CurrencyDetails,
        baseCurrencyDetails: CurrencyDetails
    ): CurrencyListCurrencyViewModel {
        val country = currencyCountryStore.getCountry(it.code)!!

        val convertedAmount = currencyFormatter.getConvertedCurrencyValue(
            baseCurrencyDetails.value,
            it.value,
            country.symbol
        )
        return CurrencyListCurrencyViewModel(
            it.code,
            convertedAmount,
            country.name
        )
    }
}
