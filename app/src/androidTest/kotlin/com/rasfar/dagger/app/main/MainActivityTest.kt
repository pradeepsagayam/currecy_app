package com.prad.dagger.app.main

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.prad.dagger.app.R
import com.prad.dagger.app.support.MockServerRobot
import com.prad.dagger.app.support.MockWebServerRule
import com.prad.dagger.app.support.UiAction
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @get:Rule
    val mockWebServerRule = MockWebServerRule()

    private val mockServerRobot = MockServerRobot(mockWebServerRule)
    private val mainActivityRobot = MainActivityRobot()

    @Test
    fun onLaunchMainScreen_onCurrencyLoadSuccess_seesTitleWithCurrencyInfo() {
        activityRule.launchActivity(null)

        mockServerRobot.performUiAction(object : UiAction {
            override fun perform() {
                mainActivityRobot.seesLoadingSpinner()
            }
        }).enqueueResponse("Currency/get.json")

        mainActivityRobot
            .seesTitle(R.string.app_name)
            .seesCountryName("Singapore")
            .seesCurrencyTemp("300.86")
    }

    @Test
    fun onLaunchMainScreen_onCurrencyLoadFailed_seesErrorMessage() {
        activityRule.launchActivity(null)

        mockServerRobot.performUiAction(object : UiAction {
            override fun perform() {
                mainActivityRobot.seesLoadingSpinner()
            }
        }).enqueueErrorResponse()

        mainActivityRobot
            .seesTitle(R.string.app_name)
            .seesErrorMessage(R.string.failed_message)
    }

    @Test
    fun onLaunchMainScreen_onLoadErrorClicksCurrencyContainer_seesRefreshCurrencyInfo() {
        activityRule.launchActivity(null)

        mockServerRobot.performUiAction(object : UiAction {
            override fun perform() {
                mainActivityRobot.seesLoadingSpinner()
            }
        }).enqueueErrorResponse()

        mainActivityRobot
            .seesErrorMessage(R.string.failed_message)

        mockServerRobot.performUiAction(object : UiAction {
            override fun perform() {
                mainActivityRobot.clicksOnCurrencyContainer()

                mainActivityRobot.seesLoadingSpinner()
            }
        }).enqueueResponse("Currency/get2.json")

        mainActivityRobot
            .seesCountryName("Singapore2")
            .seesCurrencyTemp("200.06")
    }
}
