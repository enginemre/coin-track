package com.engin.cointrack.feature.favourite.data.domainimpl.di

import com.engin.cointrack.core.domain.DeleteFavouriteCoinUseCase
import com.engin.cointrack.core.domain.GetFavouriteCoinsUseCase
import com.engin.cointrack.core.domain.UpsertFavouriteCoinUseCase
import com.engin.cointrack.feature.favourite.data.domainimpl.usecase.DeleteFavouriteCoinUseCaseImpl
import com.engin.cointrack.feature.favourite.data.domainimpl.usecase.GetFavouriteCoinsUseCaseImpl
import com.engin.cointrack.feature.favourite.data.domainimpl.usecase.UpsertFavouriteCoinUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface FavouriteDomainModule {

    @Binds
    @Singleton
    fun bindGetFavouriteUseCase(getFavouriteCoinsUseCaseImpl: GetFavouriteCoinsUseCaseImpl): GetFavouriteCoinsUseCase

    @Binds
    @Singleton
    fun bindUpsertFavouriteUseCase(upsertFavouriteCoinUseCaseImpl: UpsertFavouriteCoinUseCaseImpl): UpsertFavouriteCoinUseCase

    @Binds
    @Singleton
    fun bindDeleteFavouriteUseCase(deleteFavouriteCoinUseCaseImpl: DeleteFavouriteCoinUseCaseImpl): DeleteFavouriteCoinUseCase
}
