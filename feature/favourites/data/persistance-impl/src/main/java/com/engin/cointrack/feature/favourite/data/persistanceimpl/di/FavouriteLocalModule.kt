package com.engin.cointrack.feature.favourite.data.persistanceimpl.di

import android.content.Context
import androidx.room.Room
import com.engin.cointrack.feature.favourite.data.persistance.FavouriteLocalDataSource
import com.engin.cointrack.feature.favourite.data.persistanceimpl.FavouriteLocalDataSourceImpl
import com.engin.cointrack.feature.favourite.data.persistanceimpl.database.FavouriteDataBase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface FavouriteLocalModule {

    @Binds
    @Singleton
    fun bindFavouriteLocalDataSource(favouriteLocalDataSourceImpl: FavouriteLocalDataSourceImpl): FavouriteLocalDataSource

    companion object {

        @Provides
        @Singleton
        fun provideFavoriteCoinDataBase(
            @ApplicationContext context: Context,
        ): FavouriteDataBase {
            return Room.databaseBuilder(
                context = context,
                klass = FavouriteDataBase::class.java,
                name = "favourite_coin_db",
            ).build()
        }

        @Provides
        @Singleton
        fun provideFavouriteCoinDao(
            coinDatabase: FavouriteDataBase,
        ) = coinDatabase.favouriteCoinDao()
    }
}
