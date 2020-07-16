package com.prad.dagger.app.main.list

interface CurrencyListViewModel {

    @CurrencyListViewType
    fun getType(): Int
}
