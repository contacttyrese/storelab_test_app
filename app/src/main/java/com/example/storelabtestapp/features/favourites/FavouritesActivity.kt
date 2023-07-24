package com.example.storelabtestapp.features.favourites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.ui.Modifier
import com.example.storelabtestapp.common.theme.StoreLabTestAppTheme
import com.example.storelabtestapp.features.favourites.view.DisplayFavouriteImages
import com.example.storelabtestapp.features.favourites.view.DisplayHeading
import com.example.storelabtestapp.features.favourites.viewmodel.FavouritesViewModel
import com.example.storelabtestapp.features.tabbar.view.DisplayTabBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesActivity : ComponentActivity() {
    private val viewModel: FavouritesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val images by remember {
                viewModel.getFavouriteImages()
            }.subscribeAsState(initial = listOf())
            StoreLabTestAppTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .weight(0.2f)
                    ) {
                        DisplayHeading()
                    }
                    Column(
                        modifier = Modifier
                            .weight(0.7f)
                    ) {
                        DisplayFavouriteImages(images)
                    }
                    Column(
                        verticalArrangement = Arrangement.Bottom,
                        modifier = Modifier
                            .weight(0.1f)
                    ) {
                        DisplayTabBar(selectedTabIndex = 1)
                    }
                }
            }
        }
    }
}