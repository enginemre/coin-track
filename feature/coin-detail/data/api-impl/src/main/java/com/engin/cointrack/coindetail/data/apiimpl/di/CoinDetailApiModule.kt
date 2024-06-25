package com.engin.cointrack.coindetail.data.apiimpl.di

import com.engin.cointrack.coindetail.data.api.CoinDetailRemoteDataSource
import com.engin.cointrack.coindetail.data.apiimpl.mapper.CoinDetailMapper
import com.engin.cointrack.coindetail.data.apiimpl.retrofit.CoinDetailApi
import com.engin.cointrack.coindetail.data.apiimpl.retrofit.CoinDetailRetrofitImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoinDetailApiModule {

    @Provides
    @Singleton
    fun provideCoinDetailApi(
        retrofit: Retrofit,
    ): CoinDetailApi {
        return retrofit.create(CoinDetailApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinDetailRetrofitSource(
        coinDetailApi: CoinDetailApi,
        coinDetailMapper: CoinDetailMapper,
    ): CoinDetailRemoteDataSource {
        return CoinDetailRetrofitImpl(
            coinDetailApi = coinDetailApi,
            coinDetailMapper = coinDetailMapper,
        )
    }
}
