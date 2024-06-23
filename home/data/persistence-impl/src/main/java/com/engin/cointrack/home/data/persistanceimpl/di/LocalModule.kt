package com.engin.cointrack.home.data.persistanceimpl.di

import android.content.Context
import androidx.room.Room
import com.engin.cointrack.core.common.qualifiers.RoomSource
import com.engin.cointrack.home.data.persistance.HomeLocalDataSource
import com.engin.cointrack.home.data.persistanceimpl.mapper.HomeMapper
import com.engin.cointrack.home.data.persistanceimpl.room.HomeRoomDataSourceImpl
import com.engin.cointrack.home.data.persistanceimpl.room.database.CoinDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideCoinDataBase(
        @ApplicationContext context: Context,
    ): CoinDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = CoinDatabase::class.java,
            name = "coin_db",
        ).build()
    }

    @Provides
    @Singleton
    fun provideCoinDao(
        coinDatabase: CoinDatabase,
    ) = coinDatabase.coinDao()

    @Provides
    @Singleton
    @RoomSource
    fun provideHomeLocalRoomDataSourceRoom(
        coinDatabase: CoinDatabase,
        homeMapper: HomeMapper,
    ): HomeLocalDataSource {
        return HomeRoomDataSourceImpl(
            homeMapper = homeMapper,
            coinDatabase = coinDatabase,
        )
    }
}
