package com.prad.dagger.app.main

import com.prad.dagger.app.main.currency.CurrencyFormatter
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CurrencyFormatterTest {

    @InjectMocks
    private lateinit var subject: CurrencyFormatter

    @Test
    fun getConvertedCurrencyValue() {
        val actual = subject.getConvertedCurrencyValue(
            1.0,
            2.25,
            "symbol"
        )

        assertThat(actual).isEqualTo("symbol 2.25")
    }
}
