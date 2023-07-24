package com.example.storelabtestapp.features.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.storelabtestapp.common.theme.StoreLabTestAppTheme
import com.example.storelabtestapp.features.home.view.DisplayGetNextPageOfImages
import com.example.storelabtestapp.features.home.view.DisplayGetPreviousPageOfImages
import com.example.storelabtestapp.features.home.view.DisplayImageGallery
import com.example.storelabtestapp.features.home.viewmodel.HomeViewModel
import com.example.storelabtestapp.features.tabbar.view.DisplayTabBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StoreLabTestAppTheme {
                var pageNo by remember {
                    mutableStateOf(1)
                }
                val images by viewModel.getGalleryImages(pageNo).subscribeAsState(initial = listOf())
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .weight(0.8f)
                    ) {
                        DisplayImageGallery(images)
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(0.1f)
                            .fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .weight(0.5f)
                                    .fillMaxWidth()
                            ) {
                                DisplayGetPreviousPageOfImages(pageNo) {
                                    pageNo--
                                }
                            }
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .weight(0.5f)
                                    .fillMaxWidth()
                            ) {
                                DisplayGetNextPageOfImages(pageNo) {
                                    pageNo++
                                }
                            }
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.Bottom,
                        modifier = Modifier
                            .weight(0.1f)
                    ) {
                        DisplayTabBar(0)
                    }
                }
            }
        }
    }
}