package com.example.storelabtestapp.data.repository.fullscreenimage

import com.example.storelabtestapp.data.local.FavouriteImageDao
import com.example.storelabtestapp.data.model.fullscreenimage.FavouriteImage
import javax.inject.Inject

class FullscreenImageRepository @Inject constructor(
    private val dao: FavouriteImageDao
) {

    suspend fun addFavouriteImageToFavourites(image: FavouriteImage) {
        dao.insertFavourite(image)
    }

    suspend fun isFavouriteImageAlreadySaved(imageId: String): Boolean {
        return dao.isImageAlreadySaved(imageId)
    }
}