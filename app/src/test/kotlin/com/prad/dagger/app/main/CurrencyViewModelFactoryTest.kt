package com.prad.dagger.app.main

import com.prad.dagger.app.main.country.CountryResponse
import com.prad.dagger.app.main.currency.CurrencyFormatter
import com.prad.dagger.app.main.currency.CurrencyViewModelFactory
import com.prad.dagger.app.service.CurrencyDetails
import com.prad.dagger.app.service.CurrencyResult
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.groups.Tuple.tuple
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CurrencyViewModelFactoryTest {

    @InjectMocks
    private lateinit var subject: CurrencyViewModelFactory

    @Mock
    private lateinit var currencyCountryStore: CurrencyCountryStore

    @Mock
    private lateinit var currencyFormatter: CurrencyFormatter

    @Mock
    private lateinit var country1: CountryResponse.Country

    @Mock
    private lateinit var country2: CountryResponse.Country

    @Mock
    private lateinit var country3: CountryResponse.Country

    @Mock
    private lateinit var baseCountry: CountryResponse.Country

    @Before
    fun setUp() {
        `when`(currencyCountryStore.getCountry("currency1"))
            .thenReturn(country1)
        `when`(currencyCountryStore.getCountry("currency2"))
            .thenReturn(country2)
        `when`(currencyCountryStore.getCountry("currency3"))
            .thenReturn(country3)
        `when`(currencyCountryStore.getCountry("base"))
            .thenReturn(baseCountry)
        `when`(country1.symbol).thenReturn("symbol1")
        `when`(country2.symbol).thenReturn("symbol2")
        `when`(country3.symbol).thenReturn("symbol3")
        `when`(baseCountry.symbol).thenReturn("baseSymbol")
        `when`(country1.name).thenReturn("name1")
        `when`(country2.name).thenReturn("name2")
        `when`(country3.name).thenReturn("name3")
        `when`(baseCountry.name).thenReturn("baseName")
        `when`(
            currencyFormatter.getConvertedCurrencyValue(
                1.0,
                1.89,
                "symbol1"
            )
        ).thenReturn("convertedCurrencyValue1")
        `when`(
            currencyFormatter.getConvertedCurrencyValue(
                1.0,
                2.89,
                "symbol2"
            )
        ).thenReturn("convertedCurrencyValue2")
        `when`(
            currencyFormatter.getConvertedCurrencyValue(
                1.0,
                3.89,
                "symbol3"
            )
        ).thenReturn("convertedCurrencyValue3")

        `when`(
            currencyFormatter.getConvertedCurrencyValue(
                1.0,
                1.0,
                "baseSymbol"
            )
        ).thenReturn("baseConvertedCurrencyValue")
    }

    @Test
    fun name() {
        val currencyDetails1 = CurrencyDetails("currency1", 1.89)
        val currencyDetails2 = CurrencyDetails("currency2", 2.89)
        val currencyDetails3 = CurrencyDetails("currency3", 3.89)
        val currencyDetailsList =
            listOf(currencyDetails1, currencyDetails2, currencyDetails3)

        val success = CurrencyResult.Success("base", "date", currencyDetailsList)

        val actual = subject.generateViewModels(success)

        assertThat(actual)
            .extracting("currencyCode", "convertedAmount", "currencyName")
            .containsExactly(
                tuple("base", "baseConvertedCurrencyValue", "baseName"),
                tuple("currency1", "convertedCurrencyValue1", "name1"),
                tuple("currency2", "convertedCurrencyValue2", "name2"),
                tuple("currency3", "convertedCurrencyValue3", "name3")
            )
    }
}
