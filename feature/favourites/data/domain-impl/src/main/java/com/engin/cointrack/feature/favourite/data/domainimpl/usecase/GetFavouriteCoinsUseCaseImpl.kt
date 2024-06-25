package com.engin.cointrack.feature.favourite.data.domainimpl.usecase

import com.engin.cointrack.core.data.dispatcher.CoinTrackDispatcher
import com.engin.cointrack.core.data.dispatcher.Dispatcher
import com.engin.cointrack.core.domain.GetFavouriteCoinsUseCase
import com.engin.cointrack.core.model.Coin
import com.engin.cointrack.feature.favourite.data.persistance.FavouriteLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetFavouriteCoinsUseCaseImpl @Inject constructor(
    @Dispatcher(CoinTrackDispatcher.IO)
    private val ioDispatcher: CoroutineDispatcher,
    private val favouriteLocalDataSource: FavouriteLocalDataSource,
) : GetFavouriteCoinsUseCase {
    override fun invoke(): Flow<List<Coin>> = flow {
        val result = favouriteLocalDataSource.getFavouriteCoins()
        emit(result.getOrThrow())
    }.flowOn(ioDispatcher)
}
