package com.prad.dagger.app.main

import com.prad.dagger.app.main.currency.list.CurrencyListViewModel

class MainContract {

    interface View {

        fun showLoadingSpinner()

        fun hideLoadingSpinner()

        fun showCurrencyInfo(currencyDetails: List<CurrencyListViewModel>)

        fun showErrorMessage()
    }

    interface Presenter {

        fun setView(view: View)

        fun onViewCreated(countries: String)

        fun onCurrencyContainerClicked()

        fun onViewPaused()
    }
}
