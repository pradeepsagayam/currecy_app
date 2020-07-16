package com.prad.dagger.app.main.country

import androidx.annotation.Keep

@Keep
data class CountryResponse(val countries: List<Country>) {

    data class Country(
        val symbol: String,
        val name: String,
        val symbolNative: String,
        val decimalDigits: String,
        val rounding: String,
        val code: String,
        val namePlural: String
    )
}
