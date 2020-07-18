package com.prad.dagger.app.main

import com.prad.dagger.app.main.currency.CurrencyViewModelFactory
import com.prad.dagger.app.service.CurrencyProvider
import com.prad.dagger.app.service.CurrencyResult
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val currencyProvider: CurrencyProvider,
    private val currencyViewModelFactory: CurrencyViewModelFactory,
    private val currencyCountryStore: CurrencyCountryStore,
    private val compositeDisposable: CompositeDisposable
) : MainContract.Presenter {

    private lateinit var view: MainContract.View

    override fun setView(view: MainContract.View) {
        this.view = view
    }

    override fun onViewCreated(countries: String) {
        currencyCountryStore.saveCountries(countries)
        loadCurrencyInfo()
    }

    override fun onCurrencyContainerClicked() {
        loadCurrencyInfo()
    }

    private fun loadCurrencyInfo() {
        val disposable = currencyProvider.getCurrencyInfo()
            .subscribe {
                if (it is CurrencyResult.Success) {
                    val generateViewModels = currencyViewModelFactory.generateViewModels(it)
                    view.showCurrencyInfo(generateViewModels)
                } else {
                    view.showErrorMessage()
                }
            }
        compositeDisposable.add(disposable)
    }

    override fun onViewPaused() {
        compositeDisposable.clear()
    }
}
