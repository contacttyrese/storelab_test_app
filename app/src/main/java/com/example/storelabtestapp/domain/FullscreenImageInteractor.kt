package com.example.storelabtestapp.domain

import com.example.storelabtestapp.data.model.fullscreenimage.FavouriteImage
import com.example.storelabtestapp.data.model.home.GalleryImage
import com.example.storelabtestapp.data.repository.fullscreenimage.FullscreenImageRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FullscreenImageInteractor @Inject constructor(
    private val repository: FullscreenImageRepository
) {
    suspend fun addGalleryImageToFavourites(imageId: String, imageUrl: String) {
        val favouriteImage = FavouriteImage(imageId = imageId, imageUrl = imageUrl)
        repository.addFavouriteImageToFavourites(favouriteImage)
    }

    suspend fun assertImageIsAlreadySavedById(imageId: String): Boolean {
        return repository.isFavouriteImageAlreadySaved(imageId)
    }
}