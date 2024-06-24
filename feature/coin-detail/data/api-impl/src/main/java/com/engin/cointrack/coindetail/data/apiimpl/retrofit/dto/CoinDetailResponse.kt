package com.engin.cointrack.coindetail.data.apiimpl.retrofit.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinDetailResponse(
    @SerialName("data")
    val data: CoinDetailDto,
    val timestamp: Long?,
)
