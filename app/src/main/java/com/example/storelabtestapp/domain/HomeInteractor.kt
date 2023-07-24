package com.example.storelabtestapp.domain

import com.example.storelabtestapp.data.model.home.GalleryImage
import com.example.storelabtestapp.data.repository.home.HomeRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeInteractor @Inject constructor(
    private val repository: HomeRepository
) {
    fun getGalleryImagesByPageNoAndLimit(
        pageNo: Int, limit: Int): Single<List<GalleryImage>> {
        return repository.fetchGalleryImagesList(pageNo, limit)
            .subscribeOn(Schedulers.io())
            .doOnSuccess { images ->
                images.forEach { image ->
                    image.apply {
                        downloadUrl = downloadUrl.replace("5000/3333", "600.jpg")
                    }
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}