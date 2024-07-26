package com.engin.cointrack.feature.search.domain

import androidx.paging.PagingData
import com.engin.cointrack.core.model.Coin
import kotlinx.coroutines.flow.Flow

interface GetCoinsWithQueryUseCase {
    operator fun invoke(query: String) : Flow<PagingData<Coin>>
}
