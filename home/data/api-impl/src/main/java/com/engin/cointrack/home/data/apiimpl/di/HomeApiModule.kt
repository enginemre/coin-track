package com.engin.cointrack.home.data.apiimpl.di

import com.engin.cointrack.core.common.qualifiers.KtorSource
import com.engin.cointrack.core.common.qualifiers.RetrofitSource
import com.engin.cointrack.home.data.api.HomeRemoteSource
import com.engin.cointrack.home.data.apiimpl.BuildConfig
import com.engin.cointrack.home.data.apiimpl.ktor.HomeKtorDataSourceImpl
import com.engin.cointrack.home.data.apiimpl.mapper.HomeMapper
import com.engin.cointrack.home.data.apiimpl.retorfit.HomeRetrofitDataSourceImpl
import com.engin.cointrack.home.data.apiimpl.retorfit.HomeRetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeApiModule {

    @Singleton
    @Provides
    fun provideHomeApi(
        retrofit: Retrofit,
    ): HomeRetrofitService {
        return retrofit.create(HomeRetrofitService::class.java)
    }

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(DefaultRequest) {
                url(BuildConfig.BACKEND_URL)
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        coerceInputValues = true
                    },
                )
            }
        }
    }

    @Singleton
    @Provides
    @KtorSource
    fun provideHomeKtorRemoteSource(
        httpClient: HttpClient,
        homeMapper: HomeMapper,
    ): HomeRemoteSource {
        return HomeKtorDataSourceImpl(
            homeMapper = homeMapper,
            client = httpClient,
        )
    }

    @Singleton
    @Provides
    @RetrofitSource
    fun provideHomeRetrofitRemoteSource(
        homeRetrofitService: HomeRetrofitService,
        homeMapper: HomeMapper,
    ): HomeRemoteSource {
        return HomeRetrofitDataSourceImpl(
            homeRetrofitService = homeRetrofitService,
            homeMapper = homeMapper,
        )
    }
}
