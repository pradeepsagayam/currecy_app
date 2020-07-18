package com.prad.dagger.app.main

import com.prad.dagger.app.service.CurrencyDetails
import com.prad.dagger.app.service.CurrencyProvider
import com.prad.dagger.app.service.CurrencyResult
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    @InjectMocks
    private lateinit var subject: MainPresenter

    @Mock
    private lateinit var currencyProvider: CurrencyProvider
    @Mock
    private lateinit var compositeDisposable: CompositeDisposable

    @Mock
    private lateinit var view: MainContract.View
    @Mock
    private lateinit var currencyDetails: CurrencyDetails
    @Mock
    private lateinit var currencyResultSuccess: CurrencyResult.Success
    @Mock
    private lateinit var currencyResultFailed: CurrencyResult.Failed

//    @Before
//    fun setUp() {
//        subject.setView(view)
//
//        `when`(currencyResultSuccess.currencyData).thenReturn(currencyDetails)
//
//        `when`(currencyProvider.getCurrencyInfo("Singapore"))
//            .thenReturn(Observable.just(currencyResultSuccess))
//    }
//
//    @Test
//    fun onViewCreated_verifyProvider() {
//        subject.onViewCreated(countries)
//
//        verify(currencyProvider).getCurrencyInfo("Singapore")
//    }
//
//    @Test
//    fun onViewCreated_givenProviderSuccess_showsCurrencyInfo() {
//        subject.onViewCreated(countries)
//
//        val inOrder = inOrder(view)
//        inOrder.verify(view).showLoadingSpinner()
//        inOrder.verify(view).showCurrencyInfo(currencyDetails)
//        inOrder.verify(view).hideLoadingSpinner()
//    }
//
//    @Test
//    fun onViewCreated_givenProviderFailed_showsErrorMessage() {
//        `when`(currencyProvider.getCurrencyInfo("Singapore"))
//            .thenReturn(Observable.just(currencyResultFailed))
//
//        subject.onViewCreated(countries)
//
//        val inOrder = inOrder(view)
//        inOrder.verify(view).showLoadingSpinner()
//        inOrder.verify(view).showErrorMessage()
//        inOrder.verify(view).hideLoadingSpinner()
//    }
//
//    @Test
//    fun onCurrencyContainerClicked_verifyProvider() {
//        subject.onCurrencyContainerClicked()
//
//        verify(currencyProvider).getCurrencyInfo("Singapore")
//    }
//
//    @Test
//    fun onCurrencyContainerClicked_givenProviderSuccess_showsCurrencyInfo() {
//        subject.onCurrencyContainerClicked()
//
//        val inOrder = inOrder(view)
//        inOrder.verify(view).showLoadingSpinner()
//        inOrder.verify(view).showCurrencyInfo(currencyDetails)
//        inOrder.verify(view).hideLoadingSpinner()
//    }
//
//    @Test
//    fun onCurrencyContainerClicked_givenProviderFailed_showsErrorMessage() {
//        `when`(currencyProvider.getCurrencyInfo("Singapore"))
//            .thenReturn(Observable.just(currencyResultFailed))
//
//        subject.onCurrencyContainerClicked()
//
//        val inOrder = inOrder(view)
//        inOrder.verify(view).showLoadingSpinner()
//        inOrder.verify(view).showErrorMessage()
//        inOrder.verify(view).hideLoadingSpinner()
//    }

    @Test
    fun onViewPaused_verifyClearDisposables() {
        subject.onViewPaused()

        verify(compositeDisposable).clear()
    }

}
