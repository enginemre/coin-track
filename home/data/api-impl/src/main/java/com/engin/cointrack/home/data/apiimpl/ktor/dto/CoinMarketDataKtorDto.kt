package com.engin.cointrack.home.data.apiimpl.ktor.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinMarketDataKtorDto(
    val baseId: String,
    val baseSymbol: String,
    val exchangeId: String,
    val percentExchangeVolume: String?,
    val priceQuote: String,
    val priceUsd: String,
    val quoteId: String,
    val quoteSymbol: String,
    val rank: String,
    val tradesCount24Hr: String?,
    val updated: Long,
    val volumeUsd24Hr: String,
)
