package com.engin.cointrack.feature.authentication.data.apiimpl.di

import com.engin.cointrack.feature.authentication.data.api.AuthenticationRemoteDataSource
import com.engin.cointrack.feature.authentication.data.apiimpl.FirebaseRemoteDataSourceImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationApiModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseAuthenticationRemoteSource(
        firebaseAuth: FirebaseAuth,
    ): AuthenticationRemoteDataSource {
        return FirebaseRemoteDataSourceImpl(
            firebaseAuth = firebaseAuth,
        )
    }
}
