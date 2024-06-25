package com.engin.cointrack.feature.favourite.data.persistanceimpl

import com.engin.cointrack.core.model.Coin
import com.engin.cointrack.feature.favourite.data.persistance.FavouriteLocalDataSource
import com.engin.cointrack.feature.favourite.data.persistanceimpl.dao.FavouriteDao
import com.engin.cointrack.feature.favourite.data.persistanceimpl.entity.toCoin
import com.engin.cointrack.feature.favourite.data.persistanceimpl.entity.toFavouriteEntity
import javax.inject.Inject

class FavouriteLocalDataSourceImpl @Inject constructor(
    private val coinDao: FavouriteDao,
) : FavouriteLocalDataSource {
    override suspend fun getFavouriteCoins(): Result<List<Coin>> {
        return runCatching {
            val result = coinDao.getFavourites()
            result.map { it.toCoin() }
        }
    }

    override suspend fun upsertFavouriteCoin(coin: Coin): Result<Unit> {
        return runCatching {
            coinDao.upsertFavouriteCoin(coin.toFavouriteEntity())
        }
    }

    override suspend fun deleteFavouriteCoin(coin: Coin): Result<Int> {
        return runCatching {
            coinDao.deleteFavouriteCoin(coin.toFavouriteEntity().coinId)
        }
    }
}
