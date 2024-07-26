package com.engin.cointrack.feature.search.data.apiimpl.retrofit

import com.engin.cointrack.core.data.dto.toCoin
import com.engin.cointrack.core.model.Coin
import com.engin.cointrack.core.network.util.getBodyOrThrowError
import com.engin.cointrack.feature.search.api.SearchRemoteSource
import javax.inject.Inject

class SearchRetrofitSourceImpl @Inject constructor(
    private val searchRetrofitApi: SearchRetrofitApi,
) : SearchRemoteSource {
    override suspend fun getSearchResult(limit: Int, offset: Int, query: String): Result<List<Coin>> {
        return runCatching {
            val response = searchRetrofitApi.getCoinsByQuery(query, limit, offset).getBodyOrThrowError()
            response.data.map { it.toCoin() }
        }
    }
}
