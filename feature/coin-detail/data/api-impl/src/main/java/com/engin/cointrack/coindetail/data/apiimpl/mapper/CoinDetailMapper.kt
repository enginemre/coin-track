package com.engin.cointrack.coindetail.data.apiimpl.mapper

import android.icu.text.DecimalFormat
import com.engin.cointrack.coindetail.data.apiimpl.retrofit.dto.CoinDetailDto
import com.engin.cointrack.coindetail.domain.model.CoinDetail
import javax.inject.Inject

class CoinDetailMapper @Inject constructor() {
    private val decimalFormat = DecimalFormat("###,###.##")
    fun coinDetailToExternalModal(coinDetailDto: CoinDetailDto): CoinDetail {
        val doublePrice = coinDetailDto.priceUsd.toDoubleOrNull()
        return CoinDetail(
            id = coinDetailDto.id,
            name = coinDetailDto.name,
            symbol = coinDetailDto.symbol,
            price = "${decimalFormat.format(doublePrice)} $",
            rank = coinDetailDto.rank,
        )
    }
}
