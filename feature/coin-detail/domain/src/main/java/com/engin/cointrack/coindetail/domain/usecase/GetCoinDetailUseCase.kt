package com.engin.cointrack.coindetail.domain.usecase

import com.engin.cointrack.coindetail.domain.model.CoinDetail
import kotlinx.coroutines.flow.Flow

interface GetCoinDetailUseCase {
    operator fun invoke(id: String): Flow<CoinDetail>
}
