package com.example.storelabtestapp.data.repository.home

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.storelabtestapp.data.model.home.GalleryImage
import com.example.storelabtestapp.data.remote.home.HomeService
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeService: HomeService
) {
    fun fetchGalleryImagesList(pageNumber: Int, listLimit: Int): Single<List<GalleryImage>> {
        return homeService.getGalleryImageList(pageNumber, listLimit)
    }
}