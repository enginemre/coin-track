package com.engin.cointrack.home.data.apiimpl.ktor.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinMarketKtorResponse(
    val data: List<CoinMarketDataKtorDto>,
    val timestamp: Long,
)
