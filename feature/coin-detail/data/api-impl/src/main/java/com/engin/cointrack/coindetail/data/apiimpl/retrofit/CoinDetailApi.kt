package com.engin.cointrack.coindetail.data.apiimpl.retrofit

import com.engin.cointrack.coindetail.data.apiimpl.retrofit.dto.CoinDetailResponse
import com.engin.cointrack.coindetail.data.apiimpl.util.PathParam
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinDetailApi {

    @GET("/v2/assets/{id}")
    suspend fun getCoinDetail(@Path(PathParam.ID) id: String): Response<CoinDetailResponse>
}
