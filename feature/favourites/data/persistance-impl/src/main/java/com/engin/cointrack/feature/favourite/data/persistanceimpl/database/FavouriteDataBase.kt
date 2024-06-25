package com.engin.cointrack.feature.favourite.data.persistanceimpl.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.engin.cointrack.feature.favourite.data.persistanceimpl.dao.FavouriteDao
import com.engin.cointrack.feature.favourite.data.persistanceimpl.entity.FavouriteCoinEntity

@Database(entities = [FavouriteCoinEntity::class], version = 1)
abstract class FavouriteDataBase : RoomDatabase() {
    abstract fun favouriteCoinDao(): FavouriteDao
}
