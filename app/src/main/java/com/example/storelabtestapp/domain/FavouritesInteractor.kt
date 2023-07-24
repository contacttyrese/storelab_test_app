package com.example.storelabtestapp.domain

import com.example.storelabtestapp.data.model.fullscreenimage.FavouriteImage
import com.example.storelabtestapp.data.repository.favourites.FavouritesRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavouritesInteractor @Inject constructor(
    private val repository: FavouritesRepository
) {
    fun getListOfFavoriteImages(): Single<List<FavouriteImage>> {
        return repository.fetchListOfFavouriteImages()
            .subscribeOn(Schedulers.io())
            .doOnSuccess { list ->
                val sizeRegex = Regex("id/[0-9]+.+")
                list.forEach { image ->
                    image.apply {
                        imageUrl = imageUrl.replace(sizeRegex, "id/$imageId/300/300.jpg")
                    }
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}