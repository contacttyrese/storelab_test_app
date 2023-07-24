package com.example.storelabtestapp.data.remote.home

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.storelabtestapp.data.model.home.GalleryImage
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface HomeService {
    @Headers(
        "Accept: application/json"
    )
    @GET("/v2/list")
    fun getGalleryImageList(
        @Query("page") pageNumber: Int, @Query("limit") listLimit: Int
    ): Single<List<GalleryImage>>
}