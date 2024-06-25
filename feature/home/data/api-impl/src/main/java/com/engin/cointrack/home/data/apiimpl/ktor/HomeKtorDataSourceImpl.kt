package com.engin.cointrack.home.data.apiimpl.ktor
import com.engin.cointrack.core.model.Coin
import com.engin.cointrack.home.data.api.HomeRemoteSource
import com.engin.cointrack.home.data.apiimpl.ktor.dto.CoinMarketKtorResponse
import com.engin.cointrack.home.data.apiimpl.mapper.HomeMapper
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import javax.inject.Inject

class HomeKtorDataSourceImpl @Inject constructor(
    private val client: HttpClient,
    private val homeMapper: HomeMapper,
) : HomeRemoteSource {
    override suspend fun getCoinList(
        offset: Int,
        limit: Int,
    ): Result<List<Coin>> {
        return runCatching {
            val result = client.get {
                url("/v2/markets?quoteSymbol=USDT")
                parameter(key = "offset", value = offset)
                parameter(key = "limit", value = limit)
            }.body<CoinMarketKtorResponse>()
            result.data.map { homeMapper.coinMarketResponseToCoin(it) }
        }
    }
}
