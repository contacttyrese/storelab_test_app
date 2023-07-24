package com.example.storelabtestapp.data.model.fullscreenimage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavouriteImage(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var imageId: String = "",
    var imageUrl: String = ""
)