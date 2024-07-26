package com.engin.cointrack.feature.authentication.data.api

import com.engin.cointrack.core.model.User
import kotlinx.coroutines.flow.Flow

interface AuthenticationRemoteDataSource {
    fun getUser(email: String, password: String) : Flow<User>

    fun isUserLoggedIn() : Boolean
}
