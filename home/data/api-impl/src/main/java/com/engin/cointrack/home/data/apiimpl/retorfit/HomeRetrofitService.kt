package com.engin.cointrack.home.data.apiimpl.retorfit

import com.engin.cointrack.home.data.apiimpl.retorfit.dto.CoinMarketResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeRetrofitService {
    @GET("/v2/markets?quoteSymbol=USDT")
    suspend fun getCoinList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): Response<CoinMarketResponse>
}
