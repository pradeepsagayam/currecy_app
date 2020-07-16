package com.prad.dagger.app.service

import com.google.gson.JsonObject

data class CurrencyResponse(
    val base: String,
    val date: String,
    val rates: JsonObject
)
