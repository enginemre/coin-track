package com.engin.cointrack.home.data.domainimpl.pagingmediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.engin.cointrack.core.data.qualifiers.RetrofitSource
import com.engin.cointrack.core.data.qualifiers.RoomSource
import com.engin.cointrack.home.data.api.HomeRemoteSource
import com.engin.cointrack.home.data.persistance.HomeLocalDataSource
import com.engin.cointrack.home.data.persistance.model.entity.CoinEntity
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class CoinsRemoteMediator @Inject constructor(
    @RoomSource private val homeLocalDataSource: HomeLocalDataSource,
    @RetrofitSource private val homeRemoteDataSource: HomeRemoteSource,
) : RemoteMediator<Int, CoinEntity>() {
    @Suppress("TooGenericExceptionCaught", "ReturnCount")
    override suspend fun load(loadType: LoadType, state: PagingState<Int, CoinEntity>): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItemId = homeLocalDataSource.selectLastCoinId().getOrNull()
                    lastItemId ?: return MediatorResult.Success(endOfPaginationReached = true)
                    homeLocalDataSource.getCount().getOrNull() ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
            }
            val coins = homeRemoteDataSource
                .getCoinList(loadKey, state.config.pageSize).getOrThrow()
            homeLocalDataSource.insertCoins(
                coins = coins,
                isRefresh = LoadType.REFRESH == loadType,
            )
            MediatorResult.Success(endOfPaginationReached = coins.isEmpty())
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
