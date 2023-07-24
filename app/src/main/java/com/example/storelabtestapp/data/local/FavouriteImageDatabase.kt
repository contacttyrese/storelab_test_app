package com.example.storelabtestapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.storelabtestapp.data.model.fullscreenimage.FavouriteImage

@Database(
    entities = [FavouriteImage::class],
    version = 1
)
abstract class FavouriteImageDatabase : RoomDatabase() {
    abstract val dao: FavouriteImageDao
}