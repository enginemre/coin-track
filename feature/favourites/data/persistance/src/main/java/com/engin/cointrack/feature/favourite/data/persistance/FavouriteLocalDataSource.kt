package com.engin.cointrack.feature.favourite.data.persistance

import com.engin.cointrack.core.model.Coin

interface FavouriteLocalDataSource{
   suspend fun getFavouriteCoins() : Result<List<Coin>>

   suspend fun upsertFavouriteCoin(coin: Coin) : Result<Unit>

   suspend fun deleteFavouriteCoin(coin: Coin): Result<Int>
}
