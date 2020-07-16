package com.prad.dagger.app.main

import com.prad.dagger.app.main.country.CountryResponse.Country

interface CurrencyCountryStore {

    fun getCountries(): List<Country>?

    fun getCountry(currencyCode: String): Country?

    fun saveCountries(countries: String)
}
