package com.engin.cointrack.home.data.apiimpl.mapper

import android.icu.text.DecimalFormat
import com.engin.cointrack.core.model.Coin
import com.engin.cointrack.home.data.apiimpl.ktor.dto.CoinMarketDataKtorDto
import com.engin.cointrack.home.data.apiimpl.retorfit.dto.CoinMarketDataDto
import javax.inject.Inject

class HomeMapper @Inject constructor() {

    private val decimalFormat = DecimalFormat("###,###.##")

    fun coinMarketResponseToCoin(coinMarketResponse: CoinMarketDataDto): Coin {
        val doublePrice = coinMarketResponse.priceUsd.toDoubleOrNull()
        return Coin(
            uniqueId = 0,
            name = coinMarketResponse.baseSymbol,
            id = coinMarketResponse.baseId,
            priceStr = "${decimalFormat.format(doublePrice)} $",
            symbol = coinMarketResponse.baseSymbol,
        )
    }

    fun coinMarketResponseToCoin(coinMarketResponse: CoinMarketDataKtorDto): Coin {
        val doublePrice = coinMarketResponse.priceUsd.toDoubleOrNull()
        return Coin(
            uniqueId = 0,
            name = coinMarketResponse.baseSymbol,
            id = coinMarketResponse.baseId,
            priceStr = "${decimalFormat.format(doublePrice)} $",
            symbol = coinMarketResponse.baseSymbol,
        )
    }
}
