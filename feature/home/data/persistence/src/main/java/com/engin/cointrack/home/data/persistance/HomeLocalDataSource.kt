package com.engin.cointrack.home.data.persistance

import androidx.paging.PagingSource
import com.engin.cointrack.core.model.Coin
import com.engin.cointrack.home.data.persistance.model.entity.CoinEntity

interface HomeLocalDataSource {
    fun getCoinList(): PagingSource<Int, CoinEntity>

    suspend fun insertCoins(coins: List<Coin>, isRefresh: Boolean): Result<Boolean>

    suspend fun deleteCoins(): Result<Boolean>

    suspend fun selectLastCoinId(): Result<Coin?>

    suspend fun getCount(): Result<Int>
}
