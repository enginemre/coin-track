package com.engin.cointrack.feature.authentication.domain

import com.engin.cointrack.core.common.Resource
import com.engin.cointrack.core.model.User
import kotlinx.coroutines.flow.Flow

interface SignInUserUseCase {
    operator fun invoke(
        email: String,
        password: String
    ) : Flow<Resource<User>>
}
