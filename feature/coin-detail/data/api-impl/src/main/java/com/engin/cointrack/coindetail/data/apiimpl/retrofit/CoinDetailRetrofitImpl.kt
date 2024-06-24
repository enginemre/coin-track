package com.engin.cointrack.coindetail.data.apiimpl.retrofit

import com.engin.cointrack.coindetail.data.api.CoinDetailRemoteDataSource
import com.engin.cointrack.coindetail.data.apiimpl.mapper.CoinDetailMapper
import com.engin.cointrack.coindetail.domain.model.CoinDetail
import com.engin.cointrack.core.network.util.getBodyOrThrowError
import javax.inject.Inject

class CoinDetailRetrofitImpl @Inject constructor(
    private val coinDetailApi: CoinDetailApi,
    private val coinDetailMapper: CoinDetailMapper,
) : CoinDetailRemoteDataSource {
    override suspend fun getCoinDetail(id: String): Result<CoinDetail> {
        return runCatching {
            val response = coinDetailApi.getCoinDetail(id).getBodyOrThrowError()
            coinDetailMapper.coinDetailToExternalModal(response.data)
        }
    }
}
