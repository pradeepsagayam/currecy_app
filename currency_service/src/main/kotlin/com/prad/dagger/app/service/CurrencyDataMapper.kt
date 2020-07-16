package com.prad.dagger.app.service

import com.prad.dagger.app.service.CurrencyResult.Success
import javax.inject.Inject

class CurrencyDataMapper @Inject constructor() {

    fun mapFrom(response: CurrencyResponse): Success {
        return response.convert()
    }

    private fun CurrencyResponse.convert(): Success {

        val currencyDetailsList = rates.keySet()
            .map { key: String ->
                CurrencyDetails(key, rates.get(key).asDouble)
            }


        return Success(
            baseCurrency = base,
            date = date,
            currencyDetails = currencyDetailsList
        )
    }
}
