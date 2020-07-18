package com.prad.dagger.app.main.currency.list

interface CurrencyListViewModel {

    @CurrencyListViewType
    fun getType(): Int
}
