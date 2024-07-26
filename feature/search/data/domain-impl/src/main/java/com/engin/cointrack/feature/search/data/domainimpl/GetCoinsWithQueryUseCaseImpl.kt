package com.engin.cointrack.feature.search.data.domainimpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.engin.cointrack.core.model.Coin
import com.engin.cointrack.feature.search.api.SearchRemoteSource
import com.engin.cointrack.feature.search.data.domainimpl.pagingsource.SearchPagingSource
import com.engin.cointrack.feature.search.domain.GetCoinsWithQueryUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinsWithQueryUseCaseImpl @Inject constructor(
    private val searchRemoteSource: SearchRemoteSource,
) : GetCoinsWithQueryUseCase {
    override fun invoke(query: String): Flow<PagingData<Coin>> {
        return Pager(
            PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = PRE_FETCH_DISTANCE,
            ),
        ) {
            SearchPagingSource(
                searchRemoteSource = searchRemoteSource,
                query = query,
            )
        }.flow
    }

    companion object {
        private const val PAGE_SIZE = 20
        private const val PRE_FETCH_DISTANCE = 2
    }
}
