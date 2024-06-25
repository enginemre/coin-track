package com.engin.cointrack.home.data.domainimpl.usecase

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.engin.cointrack.core.data.dispatcher.CoinTrackDispatcher
import com.engin.cointrack.core.data.dispatcher.Dispatcher
import com.engin.cointrack.core.data.qualifiers.RetrofitSource
import com.engin.cointrack.core.data.qualifiers.RoomSource
import com.engin.cointrack.core.model.Coin
import com.engin.cointrack.domain.usecase.GetCoinsUseCase
import com.engin.cointrack.home.data.api.HomeRemoteSource
import com.engin.cointrack.home.data.domainimpl.pagingmediator.CoinsRemoteMediator
import com.engin.cointrack.home.data.persistance.HomeLocalDataSource
import com.engin.cointrack.home.data.persistance.model.entity.toCoin
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCoinsUseCaseImpl @Inject constructor(
    @Dispatcher(CoinTrackDispatcher.IO)
    private val ioDispatcher: CoroutineDispatcher,
    @RoomSource private val homeLocalDataSource: HomeLocalDataSource,
    @RetrofitSource private val homeRemoteDataSource: HomeRemoteSource,
) : GetCoinsUseCase {
    @OptIn(ExperimentalPagingApi::class)
    override fun invoke(): Flow<PagingData<Coin>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            prefetchDistance = DEFAULT_PRE_FETCH_DISTANCE,
            initialLoadSize = PAGE_SIZE,
        ),
        remoteMediator = CoinsRemoteMediator(
            homeLocalDataSource = homeLocalDataSource,
            homeRemoteDataSource = homeRemoteDataSource,
        ),
        pagingSourceFactory = {
            homeLocalDataSource.getCoinList()
        },
    ).flow.map { pagingData ->
        pagingData.map { it.toCoin() }
    }.flowOn(ioDispatcher)

    companion object {
        const val PAGE_SIZE = 15
        const val DEFAULT_PRE_FETCH_DISTANCE = 1
    }
}
