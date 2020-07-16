package com.prad.dagger.app.support

import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.ExternalResource
import java.io.IOException

class MockWebServerRule : ExternalResource {

    private val mockWebServer = MockWebServer()

    constructor()

    fun getMockServer() = mockWebServer

    @Throws(IOException::class)
    override fun before() {
        mockWebServer.start(8080)
    }

    override fun after() {
        try {
            mockWebServer.shutdown()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}

