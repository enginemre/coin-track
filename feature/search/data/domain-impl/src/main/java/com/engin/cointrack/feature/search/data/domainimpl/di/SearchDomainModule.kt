package com.engin.cointrack.feature.search.data.domainimpl.di

import com.engin.cointrack.feature.search.data.domainimpl.GetCoinsWithQueryUseCaseImpl
import com.engin.cointrack.feature.search.domain.GetCoinsWithQueryUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SearchDomainModule {

    @Binds
    @Singleton
    fun bindGetCoinsWithQueryUseCase(getCoinsWithQueryUseCaseImpl: GetCoinsWithQueryUseCaseImpl): GetCoinsWithQueryUseCase
}
