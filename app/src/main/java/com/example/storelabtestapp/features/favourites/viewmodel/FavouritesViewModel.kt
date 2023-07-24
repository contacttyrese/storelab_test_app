package com.example.storelabtestapp.features.favourites.viewmodel

import androidx.lifecycle.ViewModel
import com.example.storelabtestapp.data.model.fullscreenimage.FavouriteImage
import com.example.storelabtestapp.domain.FavouritesInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val interactor: FavouritesInteractor
) : ViewModel() {

    fun getFavouriteImages(): Single<List<FavouriteImage>> {
        return interactor.getListOfFavoriteImages()
    }
}