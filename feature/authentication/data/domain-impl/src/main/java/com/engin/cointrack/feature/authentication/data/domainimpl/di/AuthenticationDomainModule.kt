package com.engin.cointrack.feature.authentication.data.domainimpl.di

import com.engin.cointrack.core.domain.GetUserLoginStatusUseCase
import com.engin.cointrack.feature.authentication.data.domainimpl.usecase.GetUserLoginStatusUseCaseImpl
import com.engin.cointrack.feature.authentication.data.domainimpl.usecase.SignInUserUseCaseImpl
import com.engin.cointrack.feature.authentication.domain.SignInUserUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AuthenticationDomainModule {

    @Binds
    @Singleton
    fun bindSignInUseCase(signInUserUseCaseImpl: SignInUserUseCaseImpl): SignInUserUseCase

    @Binds
    @Singleton
    fun bindGetUserLoginStatusUseCase(getUserLoginStatusUseCaseImpl: GetUserLoginStatusUseCaseImpl): GetUserLoginStatusUseCase
}
