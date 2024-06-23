package com.engin.cointrack.home.data.apiimpl.retorfit.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoinMarketResponse(
    val data: List<CoinMarketDataDto>,
    val timestamp: Long,
)
