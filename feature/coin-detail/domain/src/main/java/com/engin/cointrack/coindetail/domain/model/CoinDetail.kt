package com.engin.cointrack.coindetail.domain.model

data class CoinDetail(
    val id: String,
    val name: String,
    val rank: String,
    val symbol: String,
    val price: String,
) {
    val imageUrl: String = "https://assets.coincap.io/assets/icons/${symbol.lowercase()}@2x.png"
}
