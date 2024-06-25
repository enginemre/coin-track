package com.engin.cointrack.core.domain

import com.engin.cointrack.core.common.Resource
import com.engin.cointrack.core.model.Coin
import kotlinx.coroutines.flow.Flow

interface UpsertFavouriteCoinUseCase {
    operator fun invoke(coin: Coin) : Flow<Resource<Unit>>
}
