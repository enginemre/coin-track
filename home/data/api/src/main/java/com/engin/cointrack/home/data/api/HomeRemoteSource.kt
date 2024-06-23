package com.engin.cointrack.home.data.api

import com.engin.cointrack.domain.model.Coin

interface HomeRemoteSource {
    suspend fun getCoinList(
        offset: Int,
        limit: Int,
    ): Result<List<Coin>>
}
