package com.example.storelabtestapp.features.fullscreenimage.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storelabtestapp.domain.FullscreenImageInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class FullscreenImageViewModel @Inject constructor(
    private val interactor: FullscreenImageInteractor
) : ViewModel() {

    var isSavedState by mutableStateOf("ready to save")

    fun addFavouriteImage(imageId: String, imageUrl: String) {
        viewModelScope.launch(Dispatchers.IO) {
            var isImageAlreadyInFavourites = false
            runBlocking {
                isImageAlreadyInFavourites = interactor.assertImageIsAlreadySavedById(imageId)
            }
            when (isImageAlreadyInFavourites) {
                true -> {
                    Log.i("image_view_model", "image already saved")
                    isSavedState = "not saved"
                }
                false -> {
                    interactor.addGalleryImageToFavourites(imageId, imageUrl)
                    Log.i("image_view_model", "image saved")
                    isSavedState = "saved"
                }
            }
        }
    }
}