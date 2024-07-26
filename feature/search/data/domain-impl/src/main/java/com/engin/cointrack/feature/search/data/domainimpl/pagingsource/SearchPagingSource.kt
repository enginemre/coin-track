package com.engin.cointrack.feature.search.data.domainimpl.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.engin.cointrack.core.model.Coin
import com.engin.cointrack.feature.search.api.SearchRemoteSource

private const val InitialLoadSize = 1
private const val NetworkPageSize = 20

class SearchPagingSource(
    private val searchRemoteSource: SearchRemoteSource,
    private val query: String,
) : PagingSource<Int, Coin>() {
    override fun getRefreshKey(state: PagingState<Int, Coin>): Int? {
        return null
    }

    @Suppress("TooGenericExceptionCaught")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Coin> {
        val position = params.key ?: InitialLoadSize
        val offset = if (params.key != null) ((position - 1) * NetworkPageSize) + 1 else InitialLoadSize
        return try {
            val result = searchRemoteSource.getSearchResult(
                offset = offset,
                limit = params.loadSize,
                query = query,
            )
            val data = result.getOrThrow()
            val nextKey = if (data.isEmpty()) {
                null
            } else {
                position + (params.loadSize / NetworkPageSize)
            }
            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = nextKey,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
