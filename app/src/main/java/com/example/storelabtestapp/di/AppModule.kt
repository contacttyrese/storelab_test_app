package com.example.storelabtestapp.di

import android.content.Context
import androidx.room.Room
import com.example.storelabtestapp.BaseApplication
import com.example.storelabtestapp.data.local.FavouriteImageDao
import com.example.storelabtestapp.data.local.FavouriteImageDatabase
import com.example.storelabtestapp.data.remote.home.HomeRetroFit
import com.example.storelabtestapp.data.remote.home.HomeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

    @Provides
    fun provideHomeService(): HomeService {
        return HomeRetroFit.createHomeService()
    }

    @Provides
    fun provideFavouriteImageDatabase(@ApplicationContext app: Context): FavouriteImageDatabase {
        return Room.databaseBuilder(
            app,
            FavouriteImageDatabase::class.java,
            "favourites.db"
        ).build()
    }

    @Provides
    fun provideFavouriteImageDao(db: FavouriteImageDatabase): FavouriteImageDao {
        return db.dao
    }

}