package com.engin.cointrack.coindetail.data.api

import com.engin.cointrack.coindetail.domain.model.CoinDetail

interface CoinDetailRemoteDataSource {
    suspend fun getCoinDetail(id: String) : Result<CoinDetail>
}
