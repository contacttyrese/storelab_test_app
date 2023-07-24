package com.example.storelabtestapp.features.home.viewmodel

import androidx.lifecycle.ViewModel
import com.example.storelabtestapp.data.model.home.GalleryImage
import com.example.storelabtestapp.domain.HomeInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val interactor: HomeInteractor
) : ViewModel() {

    fun getGalleryImages(pageNumber: Int = 1, limit: Int = 10): Single<List<GalleryImage>> {
        return interactor.getGalleryImagesByPageNoAndLimit(pageNumber, limit)
    }

}