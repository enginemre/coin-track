package com.engin.cointrack.feature.favourite.data.persistanceimpl.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.engin.cointrack.core.model.Coin

@Entity(tableName = "favourite_coin")
data class FavouriteCoinEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val coinId: String,
    val name: String,
    val symbol: String,
    val priceStr: String,
    val imageUrl: String,
)

fun FavouriteCoinEntity.toCoin(): Coin {
    return Coin(
        uniqueId = id,
        id = coinId,
        name = name,
        symbol = symbol,
        priceStr = priceStr,
    )
}

fun Coin.toFavouriteEntity(): FavouriteCoinEntity {
    return FavouriteCoinEntity(
        id = uniqueId,
        coinId = id,
        name = name,
        symbol = symbol,
        priceStr = priceStr,
        imageUrl = imageUrl,
    )
}
