package com.engin.cointrack.domain.usecase

import androidx.paging.PagingData
import com.engin.cointrack.core.model.Coin
import kotlinx.coroutines.flow.Flow

interface GetCoinsUseCase {
    operator fun invoke(): Flow<PagingData<Coin>>
}
