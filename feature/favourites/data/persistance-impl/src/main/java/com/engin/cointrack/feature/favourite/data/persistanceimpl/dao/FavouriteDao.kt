package com.engin.cointrack.feature.favourite.data.persistanceimpl.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.engin.cointrack.feature.favourite.data.persistanceimpl.entity.FavouriteCoinEntity

@Dao
interface FavouriteDao {

    @Query("SELECT * FROM favourite_coin")
    suspend fun getFavourites(): List<FavouriteCoinEntity>

    @Upsert
    suspend fun upsertFavouriteCoin(favouriteCoinEntity: FavouriteCoinEntity)

    @Query("DELETE FROM favourite_coin WHERE coinId = :id")
    suspend fun deleteFavouriteCoin(id: String): Int
}
