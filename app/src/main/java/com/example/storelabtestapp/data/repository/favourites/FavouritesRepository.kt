package com.example.storelabtestapp.data.repository.favourites

import com.example.storelabtestapp.data.local.FavouriteImageDao
import com.example.storelabtestapp.data.model.fullscreenimage.FavouriteImage
import io.reactivex.Single
import javax.inject.Inject

class FavouritesRepository @Inject constructor(
    private val dao: FavouriteImageDao
) {
    fun fetchListOfFavouriteImages(): Single<List<FavouriteImage>> {
        return dao.getFavouriteImagesOrderedById()
    }
}