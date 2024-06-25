package com.engin.cointrack.feature.favourite.data.domainimpl.usecase

import com.engin.cointrack.core.common.Resource
import com.engin.cointrack.core.common.asResource
import com.engin.cointrack.core.data.dispatcher.CoinTrackDispatcher
import com.engin.cointrack.core.data.dispatcher.Dispatcher
import com.engin.cointrack.core.domain.UpsertFavouriteCoinUseCase
import com.engin.cointrack.core.model.Coin
import com.engin.cointrack.feature.favourite.data.persistance.FavouriteLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UpsertFavouriteCoinUseCaseImpl @Inject constructor(
    @Dispatcher(CoinTrackDispatcher.IO)
    private val ioDispatcher: CoroutineDispatcher,
    private val favouriteLocalDataSource: FavouriteLocalDataSource,
) : UpsertFavouriteCoinUseCase {
    override fun invoke(coin: Coin): Flow<Resource<Unit>> = flow {
        val result = favouriteLocalDataSource.upsertFavouriteCoin(coin)
        emit(result.getOrThrow())
    }.flowOn(ioDispatcher).asResource()
}
