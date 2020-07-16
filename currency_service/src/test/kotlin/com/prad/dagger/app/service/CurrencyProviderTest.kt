package com.prad.dagger.app.service

import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CurrencyProviderTest {

    @InjectMocks
    private lateinit var subject: CurrencyProvider

    @Mock
    private lateinit var currencyService: CurrencyService

    @Mock
    private lateinit var currencyDataMapper: CurrencyDataMapper

    @Mock
    private lateinit var response: CurrencyResponse

    @Mock
    private lateinit var success: CurrencyResult.Success

    @Before
    fun setUp() {
        `when`(currencyService.getCurrency())
            .thenReturn(Observable.just(response))

        `when`(currencyDataMapper.mapFrom(response))
            .thenReturn(success)
    }

    @Test
    fun getTrendingDetails_verifyServiceCall() {
        subject.getCurrencyInfo().test()

        verify(currencyService).getCurrency()
    }

    @Test
    fun getTrendingDetails_givenServiceWithResponse_verifyMapperCall() {
        subject.getCurrencyInfo().test()

        verify(currencyDataMapper).mapFrom(response)
    }

    @Test
    fun getTrendingDetails_givenResponseWithData_returnSuccessResultWithData() {
        val actual = subject.getCurrencyInfo().test()

        actual.assertValue { result ->
            result is CurrencyResult.Success
        }
    }

    @Test
    fun getTrendingDetails_givenErrorResponse_returnFailureResult() {
        `when`(currencyService.getCurrency())
            .thenReturn(Observable.error(Throwable()))

        val actual = subject.getCurrencyInfo().test()

        actual.assertValue { result -> result is CurrencyResult.Failed }
    }
}
