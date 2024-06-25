package com.engin.cointrack.home.data.persistanceimpl.datastore

import androidx.paging.PagingSource
import com.engin.cointrack.core.model.Coin
import com.engin.cointrack.home.data.persistance.HomeLocalDataSource
import com.engin.cointrack.home.data.persistance.model.entity.CoinEntity

class HomeDataStoreDataSourceImpl : HomeLocalDataSource {
    override fun getCoinList(): PagingSource<Int, CoinEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun insertCoins(coins: List<Coin>, isRefresh: Boolean): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCoins(): Result<Boolean> {
        return runCatching {
            true
        }
    }

    override suspend fun selectLastCoinId(): Result<Coin?> {
        TODO("Not yet implemented")
    }

    override suspend fun getCount(): Result<Int> {
        TODO("Not yet implemented")
    }
}
