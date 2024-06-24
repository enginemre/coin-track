package com.engin.cointrack.coindetail.data.domainimpl.usecase

import com.engin.cointrack.coindetail.data.api.CoinDetailRemoteDataSource
import com.engin.cointrack.coindetail.domain.model.CoinDetail
import com.engin.cointrack.coindetail.domain.usecase.GetCoinDetailUseCase
import com.engin.cointrack.core.data.dispatcher.CoinTrackDispatcher
import com.engin.cointrack.core.data.dispatcher.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCoinDetailUseCaseImpl @Inject constructor(
    @Dispatcher(CoinTrackDispatcher.IO)
    private val ioDispatcher: CoroutineDispatcher,
    private val coinDetailRemoteDataSource: CoinDetailRemoteDataSource,
) : GetCoinDetailUseCase {
    override fun invoke(id: String): Flow<CoinDetail> = flow {
        val result = coinDetailRemoteDataSource.getCoinDetail(id)
        emit(result.getOrThrow())
    }.flowOn(ioDispatcher)
}
