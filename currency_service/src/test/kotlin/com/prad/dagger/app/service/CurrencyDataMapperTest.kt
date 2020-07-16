package com.prad.dagger.app.service

import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.groups.Tuple.tuple
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CurrencyDataMapperTest {

    @InjectMocks
    private lateinit var subject: CurrencyDataMapper

    @Test
    fun mapFrom_givenResponse_returnsCurrencyData() {
        val actual = subject.mapFrom(getResponse())

        assertThat(actual.baseCurrency).isEqualTo("base")
        assertThat(actual.date).isEqualTo("date")
        assertThat(actual.currencyDetails)
            .extracting("code", "value")
            .containsExactly(
                tuple("key1", 1.11),
                tuple("key2", 1.0),
                tuple("key3", 2.11),
                tuple("key4", 3.11),
                tuple("key5", 4.11),
                tuple("key6", 5.11),
                tuple("key7", 6.11)
            )
    }

    private fun getResponse() = CurrencyResponse(
        "base",
        "date",
        buildJsonObject()
    )

    private fun buildJsonObject(): JsonObject {

        val jsonObject = JsonObject()

        jsonObject.add("key1", JsonPrimitive(1.11))
        jsonObject.add("key2", JsonPrimitive(1))
        jsonObject.add("key3", JsonPrimitive(2.11))
        jsonObject.add("key4", JsonPrimitive(3.11))
        jsonObject.add("key5", JsonPrimitive(4.11))
        jsonObject.add("key6", JsonPrimitive(5.11))
        jsonObject.add("key7", JsonPrimitive(6.11))

        return jsonObject
    }
}
