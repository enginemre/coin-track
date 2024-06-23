package com.engin.cointrack.home.data.apiimpl.retorfit

import com.engin.cointrack.core.network.util.getBodyOrThrowError
import com.engin.cointrack.domain.model.Coin
import com.engin.cointrack.home.data.api.HomeRemoteSource
import com.engin.cointrack.home.data.apiimpl.mapper.HomeMapper
import javax.inject.Inject

class HomeRetrofitDataSourceImpl @Inject constructor(
    private val homeRetrofitService: HomeRetrofitService,
    private val homeMapper: HomeMapper,
) : HomeRemoteSource {
    override suspend fun getCoinList(
        offset: Int,
        limit: Int,
    ): Result<List<Coin>> {
        return runCatching {
            val response = homeRetrofitService.getCoinList(offset, limit).getBodyOrThrowError()
            response.data.map { homeMapper.coinMarketResponseToCoin(it) }
        }
    }
}
