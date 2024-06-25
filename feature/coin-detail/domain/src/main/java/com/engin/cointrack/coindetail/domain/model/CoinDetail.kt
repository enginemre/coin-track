package com.engin.cointrack.coindetail.domain.model

import com.engin.cointrack.core.model.Coin

data class CoinDetail(
    val id: String,
    val name: String,
    val rank: String,
    val symbol: String,
    val price: String,
    val isFavourite: Boolean = false
) {
    val imageUrl: String = "https://assets.coincap.io/assets/icons/${symbol.lowercase()}@2x.png"
}


fun CoinDetail.toCoin(): Coin {
    return Coin(
        uniqueId = 0,
        id = id,
        name = name,
        priceStr = price,
        symbol = symbol,
    )
}
