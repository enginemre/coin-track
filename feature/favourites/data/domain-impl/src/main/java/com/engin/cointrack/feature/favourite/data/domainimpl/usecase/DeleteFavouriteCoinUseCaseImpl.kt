package com.engin.cointrack.feature.favourite.data.domainimpl.usecase

import com.engin.cointrack.core.common.Resource
import com.engin.cointrack.core.common.asResource
import com.engin.cointrack.core.data.dispatcher.CoinTrackDispatcher
import com.engin.cointrack.core.data.dispatcher.Dispatcher
import com.engin.cointrack.core.domain.DeleteFavouriteCoinUseCase
import com.engin.cointrack.core.model.Coin
import com.engin.cointrack.feature.favourite.data.persistance.FavouriteLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DeleteFavouriteCoinUseCaseImpl @Inject constructor(
    @Dispatcher(CoinTrackDispatcher.IO)
    private val ioDispatcher: CoroutineDispatcher,
    private val favouriteLocalDataSource: FavouriteLocalDataSource,
) : DeleteFavouriteCoinUseCase {
    override fun invoke(coin: Coin): Flow<Resource<Int>> = flow {
        val result = favouriteLocalDataSource.deleteFavouriteCoin(coin)
        emit(result.getOrThrow())
    }.flowOn(ioDispatcher).asResource()
}
