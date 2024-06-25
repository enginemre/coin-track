package com.engin.cointrack.core.model

data class Coin(
    val uniqueId: Int,
    val id: String,
    val name: String,
    val priceStr: String,
    val symbol: String,
) {
    val imageUrl = "https://assets.coincap.io/assets/icons/${symbol.lowercase()}@2x.png"
}
