package com.engin.cointrack.feature.search.data.apiimpl.retrofit

import com.engin.cointrack.core.data.dto.CoinMarketResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRetrofitApi {

    @GET("v2/markets?quoteSymbol=USDT")
    suspend fun getCoinsByQuery(
        @Query("baseSymbol") baseId: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): Response<CoinMarketResponse>
}
