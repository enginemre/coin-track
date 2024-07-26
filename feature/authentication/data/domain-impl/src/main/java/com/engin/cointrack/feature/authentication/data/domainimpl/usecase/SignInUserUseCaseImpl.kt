package com.engin.cointrack.feature.authentication.data.domainimpl.usecase

import com.engin.cointrack.core.common.Resource
import com.engin.cointrack.core.common.asResource
import com.engin.cointrack.core.data.dispatcher.CoinTrackDispatcher
import com.engin.cointrack.core.data.dispatcher.Dispatcher
import com.engin.cointrack.core.model.User
import com.engin.cointrack.feature.authentication.data.api.AuthenticationRemoteDataSource
import com.engin.cointrack.feature.authentication.domain.SignInUserUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SignInUserUseCaseImpl @Inject constructor(
    @Dispatcher(CoinTrackDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
    private val authenticationRemoteDataSource: AuthenticationRemoteDataSource,
) : SignInUserUseCase {
    override fun invoke(email: String, password: String): Flow<Resource<User>> {
        return authenticationRemoteDataSource.getUser(
            email = email,
            password = password,
        ).flowOn(ioDispatcher).asResource()
    }
}
