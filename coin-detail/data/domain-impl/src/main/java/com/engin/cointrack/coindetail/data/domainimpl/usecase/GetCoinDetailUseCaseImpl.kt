package com.engin.cointrack.coindetail.data.domainimpl.usecase

import com.engin.cointrack.coindetail.domain.model.CoinDetail
import com.engin.cointrack.coindetail.domain.usecase.GetCoinDetailUseCase
import com.engin.cointrack.core.common.Resource
import com.engin.cointrack.core.common.asResource
import com.engin.cointrack.core.common.base.dispatcher.CoinTrackDispatcher
import com.engin.cointrack.core.common.base.dispatcher.Dispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCoinDetailUseCaseImpl @Inject constructor(
    @Dispatcher(CoinTrackDispatcher.IO)
    private val ioDispatcher: CoroutineDispatcher,
) : GetCoinDetailUseCase {
    override fun invoke(id: String): Flow<Resource<CoinDetail>> = flow {
        TODO("Not yet implemented")
    }.flowOn(ioDispatcher).asResource()
}
