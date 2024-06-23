package com.engin.cointrack.coindetail.domain.usecase

import com.engin.cointrack.coindetail.domain.model.CoinDetail
import com.engin.cointrack.core.common.Resource
import kotlinx.coroutines.flow.Flow

interface GetCoinDetailUseCase {
    operator fun invoke(id: String): Flow<Resource<CoinDetail>>
}
