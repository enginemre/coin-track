package com.engin.cointrack.home.data.persistanceimpl.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.engin.cointrack.home.data.persistanceimpl.room.entity.CoinEntity

@Dao
interface CoinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoin(coins: List<CoinEntity>)

    @Query("SELECT * FROM coins")
    fun pagingSource(): PagingSource<Int, CoinEntity>

    @Query("DELETE FROM coins")
    suspend fun clearAll()

    @Query("SELECT * FROM coins WHERE id = (SELECT MAX(id) FROM coins) ")
    suspend fun selectLast(): CoinEntity?

    @Query("SELECT COUNT(*) FROM coins")
    suspend fun getCoinCount(): Int
}
