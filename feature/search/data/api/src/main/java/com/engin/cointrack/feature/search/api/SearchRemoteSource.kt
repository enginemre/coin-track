package com.engin.cointrack.feature.search.api

import com.engin.cointrack.core.model.Coin

interface SearchRemoteSource {
    suspend fun getSearchResult(limit: Int, offset: Int, query: String): Result<List<Coin>>
}
