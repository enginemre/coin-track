package com.engin.cointrack.home.data.domainimpl.di

import com.engin.cointrack.domain.usecase.GetCoinsUseCase
import com.engin.cointrack.home.data.domainimpl.usecase.GetCoinsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface HomeDomainModule {

    @Binds
    @Singleton
    fun bindGetCoinsUseCase(getCoinsUseCaseImpl: GetCoinsUseCaseImpl): GetCoinsUseCase
}
