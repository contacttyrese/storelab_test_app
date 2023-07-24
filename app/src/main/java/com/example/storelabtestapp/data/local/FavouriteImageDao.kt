package com.example.storelabtestapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.storelabtestapp.data.model.fullscreenimage.FavouriteImage
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface FavouriteImageDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertFavourite(favouriteImage: FavouriteImage)

    @Delete
    suspend fun deleteFavourite(favouriteImage: FavouriteImage)

    @Query("SELECT * FROM favouriteimage ORDER BY id ASC")
    fun getFavouriteImagesOrderedById(): Single<List<FavouriteImage>>

    @Query("SELECT EXISTS (SELECT 1 FROM favouriteimage WHERE imageId = :imageId)")
    fun isImageAlreadySaved(imageId: String): Boolean
}