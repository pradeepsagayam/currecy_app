package com.prad.dagger.app.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.prad.dagger.app.R
import com.prad.dagger.app.main.list.CurrencyListAdapter
import com.prad.dagger.app.main.list.CurrencyListViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_currency.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    @Inject
    lateinit var currencyAdapter: CurrencyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbarTitle()
        presenter.setView(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = currencyAdapter
        presenter.onViewCreated(getCountries())
    }

    private fun getCountries(): String {
        return resources
            .openRawResource(R.raw.countries)
            .bufferedReader()
            .use { it.readText() }
    }

    private fun setupToolbarTitle() {
        setSupportActionBar(toolbar)
        this.supportActionBar?.setTitle(R.string.page_title_converter)
    }


    override fun onPause() {
        super.onPause()

        presenter.onViewPaused()
    }

    override fun showLoadingSpinner() {
        TODO("Not yet implemented")
    }

    override fun hideLoadingSpinner() {
        TODO("Not yet implemented")
    }

    override fun showCurrencyInfo(currencyDetails: List<CurrencyListViewModel>) {
        currencyAdapter.setViewModels(currencyDetails)
    }

    override fun showErrorMessage() {
        TODO("Not yet implemented")
    }
}
