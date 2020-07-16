package com.prad.dagger.app.main.list.viewmodels

import com.prad.dagger.app.main.list.CurrencyListViewModel
import com.prad.dagger.app.main.list.CurrencyListViewType.Companion.CURRENCY

class CurrencyListCurrencyViewModel(
    val currencyCode: String,
    val convertedAmount: String,
    val currencyName: String,
    val fromBaseCurrency: String
) : CurrencyListViewModel {

    override fun getType(): Int {
        return CURRENCY
    }
}
