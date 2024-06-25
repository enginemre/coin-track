package com.engin.cointrack.home.data.persistance.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.engin.cointrack.core.model.Coin

@Entity(tableName = "coins")
data class CoinEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val coinId: String,
    val name: String,
    val symbol: String,
    val priceStr: String,
    val imageUrl: String,
) {
    constructor(
        coinId: String,
        name: String,
        symbol: String,
        priceStr: String,
        imageUrl: String,
    ) : this(
        0,
        coinId,
        name,
        symbol,
        priceStr,
        imageUrl,
    )
}

fun CoinEntity.toCoin(): Coin {
    return Coin(
        uniqueId = id,
        id = coinId,
        name = name,
        symbol = symbol,
        priceStr = priceStr,
    )
}
