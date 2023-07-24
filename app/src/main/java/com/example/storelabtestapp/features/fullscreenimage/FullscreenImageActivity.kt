package com.example.storelabtestapp.features.fullscreenimage

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.storelabtestapp.features.fullscreenimage.view.DisplayAddToFavourites
import com.example.storelabtestapp.features.fullscreenimage.view.DisplayBackButton
import com.example.storelabtestapp.features.fullscreenimage.view.DisplayFullscreenImage
import com.example.storelabtestapp.features.fullscreenimage.viewmodel.FullscreenImageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FullscreenImageActivity : ComponentActivity() {
    private val viewModel: FullscreenImageViewModel by viewModels()
    private var _id = ""
    private var _url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.extras?.getString("idOfSelectedImage")?.let { id ->
            _id = id
            Log.i("fullscreen_act", "id is now: $_id")
        } ?: kotlin.run {
            RuntimeException("id in extras should not be null")
        }
        intent.extras?.getString("urlOfSelectedImage")?.let { url ->
            _url = url.replace("600.jpg", "900/1400.jpg")
            Log.i("fullscreen_act", "url is now: $_url")
        } ?: kotlin.run {
            RuntimeException("url in extras should not be null")
        }

        setContent {
            val context = LocalContext.current
            val didImageSave = viewModel.isSavedState
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {
                    DisplayFullscreenImage(_url)
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                ) {
                    DisplayBackButton()
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                ) {
                    DisplayAddToFavourites {
                        viewModel.addFavouriteImage(_id, _url)
                    }
                }
                Toast.makeText(
                    context,
                    when (didImageSave) {
                        "saved" -> "image was added to favourites"
                        "not saved" -> "image not added to favourites as already added"
                        "ready to save" -> "ready to save image"
                        else -> "unexpected scenario when saving"
                    },
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}