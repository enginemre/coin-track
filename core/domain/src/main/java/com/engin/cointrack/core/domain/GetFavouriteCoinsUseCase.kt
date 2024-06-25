package com.engin.cointrack.core.domain

import com.engin.cointrack.core.model.Coin
import kotlinx.coroutines.flow.Flow

interface GetFavouriteCoinsUseCase{
    operator fun invoke() : Flow<List<Coin>>
}
