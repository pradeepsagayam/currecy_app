package com.prad.dagger.app.main.currency.list.viewmodels

import com.prad.dagger.app.main.currency.list.CurrencyListViewModel
import com.prad.dagger.app.main.currency.list.CurrencyListViewType.Companion.CURRENCY

class CurrencyListCurrencyViewModel(
    val currencyCode: String,
    val convertedAmount: String,
    val currencyName: String
) : CurrencyListViewModel {

    override fun getType(): Int {
        return CURRENCY
    }
}
