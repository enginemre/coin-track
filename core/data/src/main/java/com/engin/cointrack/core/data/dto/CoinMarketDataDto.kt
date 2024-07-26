package com.engin.cointrack.core.data.dto

import android.icu.text.DecimalFormat
import com.engin.cointrack.core.model.Coin
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoinMarketDataDto(
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


fun CoinMarketDataDto.toCoin(): Coin {
    val decimalFormat = DecimalFormat("###,###.##")
    val doublePrice = this.priceUsd.toDoubleOrNull()
    return Coin(
        uniqueId = 0,
        name = this.baseSymbol,
        id = this.baseId,
        priceStr = "${decimalFormat.format(doublePrice)} $",
        symbol = this.baseSymbol,
    )
}
