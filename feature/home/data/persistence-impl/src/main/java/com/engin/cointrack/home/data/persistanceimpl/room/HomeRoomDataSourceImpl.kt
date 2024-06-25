package com.engin.cointrack.home.data.persistanceimpl.room

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.engin.cointrack.core.model.Coin
import com.engin.cointrack.home.data.persistance.HomeLocalDataSource
import com.engin.cointrack.home.data.persistance.model.entity.CoinEntity
import com.engin.cointrack.home.data.persistance.model.entity.toCoin
import com.engin.cointrack.home.data.persistanceimpl.mapper.HomeMapper
import com.engin.cointrack.home.data.persistanceimpl.room.database.CoinDatabase
import javax.inject.Inject

class HomeRoomDataSourceImpl @Inject constructor(
    private val coinDatabase: CoinDatabase,
    private val homeMapper: HomeMapper,
) : HomeLocalDataSource {

    private val dao = coinDatabase.coinDao()
    override fun getCoinList(): PagingSource<Int, CoinEntity> {
        return dao.pagingSource()
    }

    override suspend fun insertCoins(
        coins: List<Coin>,
        isRefresh: Boolean,
    ): Result<Boolean> {
        return runCatching {
            coinDatabase.withTransaction {
                if (isRefresh) {
                    dao.clearAll()
                }
                dao.insertCoin(coins.map { homeMapper.coinToCoinEntity(it) })
            }
            true
        }
    }

    override suspend fun deleteCoins(): Result<Boolean> {
        return runCatching {
            dao.clearAll()
            true
        }
    }

    override suspend fun selectLastCoinId(): Result<Coin?> {
        return runCatching {
            dao.selectLast()?.toCoin()
        }
    }

    override suspend fun getCount(): Result<Int> {
        return runCatching {
            dao.getCoinCount()
        }
    }
}
