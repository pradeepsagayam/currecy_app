package com.prad.dagger.app.main.country

import com.google.gson.GsonBuilder
import com.prad.dagger.app.main.CurrencyCountryStore
import com.prad.dagger.app.main.country.CountryResponse.Country
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrencyCurrencyCountryProvider @Inject constructor() :
    CurrencyCountryStore {

    private lateinit var countryResponse: CountryResponse

    override fun saveCountries(countries: String) {
        val gson = GsonBuilder().create()

        this.countryResponse = gson.fromJson(countries, CountryResponse::class.java)
    }

    override fun getCountries(): List<Country>? {
        return this.countryResponse.countries
    }

    override fun getCountry(currencyCode: String): Country? {
        return countryResponse.countries.find { it.code == currencyCode }
    }
}
