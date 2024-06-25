package com.engin.cointrack.home.data.persistanceimpl.mapper

import com.engin.cointrack.domain.model.Coin
import com.engin.cointrack.home.data.persistance.model.entity.CoinEntity
import javax.inject.Inject

class HomeMapper @Inject constructor() {

    fun coinToCoinEntity(coin: Coin): CoinEntity {
        return CoinEntity(
            id = coin.uniqueId,
            coinId = coin.id,
            name = coin.name,
            symbol = coin.symbol,
            imageUrl = coin.imageUrl,
            priceStr = coin.priceStr,
        )
    }

    fun coinEntityToCoin(coinEntity: CoinEntity): Coin {
        return Coin(
            uniqueId = coinEntity.id,
            id = coinEntity.coinId,
            name = coinEntity.name,
            symbol = coinEntity.symbol,
            priceStr = coinEntity.priceStr,
        )
    }
}
