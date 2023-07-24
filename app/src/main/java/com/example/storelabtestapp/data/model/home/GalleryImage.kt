package com.example.storelabtestapp.data.model.home

import com.squareup.moshi.Json

data class GalleryImage constructor(
    @field:Json(name = "id")
    val id: String = "",
    @field:Json(name = "author")
    val author: String = "",
    @field:Json(name = "width")
    val width: Int = 0,
    @field:Json(name = "height")
    val height: Int = 0,
    @field:Json(name = "url")
    val url: String = "",
    @field:Json(name = "download_url")
    var downloadUrl: String = ""
)