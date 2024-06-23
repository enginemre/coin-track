package com.engin.cointrack.coindetail.data.domainimpl.di

import com.engin.cointrack.coindetail.data.domainimpl.usecase.GetCoinDetailUseCaseImpl
import com.engin.cointrack.coindetail.domain.usecase.GetCoinDetailUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CoinDetailDomainModule {

    @Binds
    @Singleton
    fun bindGetCoinDetailUseCase(impl: GetCoinDetailUseCaseImpl): GetCoinDetailUseCase
}
