package com.engin.cointrack.feature.search.data.apiimpl.di

import com.engin.cointrack.feature.search.api.SearchRemoteSource
import com.engin.cointrack.feature.search.data.apiimpl.retrofit.SearchRetrofitApi
import com.engin.cointrack.feature.search.data.apiimpl.retrofit.SearchRetrofitSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SearchApiModule {

    @Singleton
    @Provides
    fun provideSearchApi(
        retrofit: Retrofit,
    ): SearchRetrofitApi {
        return retrofit.create(SearchRetrofitApi::class.java)
    }

    @Singleton
    @Provides
    fun provideSearchRetrofitRemoteSource(
        searchRetrofitApi: SearchRetrofitApi,
    ): SearchRemoteSource {
        return SearchRetrofitSourceImpl(
            searchRetrofitApi = searchRetrofitApi,
        )
    }
}
