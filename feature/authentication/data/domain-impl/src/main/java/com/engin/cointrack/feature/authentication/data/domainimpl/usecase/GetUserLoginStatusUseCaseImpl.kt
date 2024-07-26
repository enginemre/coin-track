package com.engin.cointrack.feature.authentication.data.domainimpl.usecase

import com.engin.cointrack.core.domain.GetUserLoginStatusUseCase
import com.engin.cointrack.feature.authentication.data.api.AuthenticationRemoteDataSource
import javax.inject.Inject

class GetUserLoginStatusUseCaseImpl @Inject constructor(
    private val authenticationRemoteDataSource: AuthenticationRemoteDataSource,
) : GetUserLoginStatusUseCase {
    override fun invoke(): Boolean {
        return authenticationRemoteDataSource.isUserLoggedIn()
    }
}
