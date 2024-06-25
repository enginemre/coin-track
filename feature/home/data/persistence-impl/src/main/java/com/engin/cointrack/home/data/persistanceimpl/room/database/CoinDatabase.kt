package com.engin.cointrack.home.data.persistanceimpl.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.engin.cointrack.home.data.persistance.model.entity.CoinEntity
import com.engin.cointrack.home.data.persistanceimpl.room.dao.CoinDao

@Database(entities = [CoinEntity::class], version = 1)
abstract class CoinDatabase : RoomDatabase() {
    abstract fun coinDao(): CoinDao
}
