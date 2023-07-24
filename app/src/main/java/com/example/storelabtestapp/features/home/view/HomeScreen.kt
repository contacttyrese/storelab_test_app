package com.example.storelabtestapp.features.home.view

import android.content.ContextWrapper
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.storelabtestapp.common.theme.StoreLabTestAppTheme
import com.example.storelabtestapp.data.model.home.GalleryImage
import com.example.storelabtestapp.features.fullscreenimage.FullscreenImageActivity

@Composable
fun DisplayImageGallery(images: List<GalleryImage>) {
    var firstListOfImages: List<GalleryImage>
    var secondListOfImages: List<GalleryImage>
    if (images.isNotEmpty()) {
        val halfOfImages = images.size/2
        firstListOfImages = images.subList(0, halfOfImages)
        secondListOfImages = images.subList(halfOfImages, images.size)

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.5f)
            ) {
                items(
                    items = firstListOfImages,
                    itemContent = { item ->
                        DisplayGalleryImage(item)
                    }
                )
            }
            LazyColumn(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.5f)
            ) {
                items(
                    items = secondListOfImages,
                    itemContent = { item ->
                        DisplayGalleryImage(item)
                    }
                )
            }
        }
    }
}

@Composable
fun DisplayGalleryImage(image: GalleryImage) {
    val context = LocalContext.current
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(image.downloadUrl)
            .crossfade(true)
            .build(),
        contentDescription = "image",
        modifier = Modifier
            .clickable {
                val intent = Intent(context, FullscreenImageActivity::class.java)
                intent.putExtra("idOfSelectedImage", image.id)
                intent.putExtra("urlOfSelectedImage", image.downloadUrl)
                ContextWrapper(context).startActivity(intent)
            }
    )
}

@Composable
fun DisplayGetNextPageOfImages(pageNo: Int, onClick: ()->Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(0.6f)
    ) {
        Text(
            text = "Page ${pageNo+1}",
            fontSize = 20.sp
        )
    }
}

@Composable
fun DisplayGetPreviousPageOfImages(pageNo: Int, onClick: ()->Unit) {
    Button(
        enabled = when (pageNo > 1) {
            true -> true
            false -> false
        },
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(0.6f)
    ) {
        Text(
            text = "Page ${pageNo-1}",
            fontSize = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDisplayImageGallery() {
    val galleryImages = listOf(
        GalleryImage(id = "1", downloadUrl = "https://picsum.photos/id/29/600"),
        GalleryImage(id = "2", downloadUrl = "https://picsum.photos/id/30/600"),
        GalleryImage(id = "3", downloadUrl = "https://picsum.photos/id/31/600"),
        GalleryImage(id = "4", downloadUrl = "https://picsum.photos/id/32/600"),
        GalleryImage(id = "5", downloadUrl = "https://picsum.photos/id/33/600"),
        GalleryImage(id = "6", downloadUrl = "https://picsum.photos/id/34/600"),
        GalleryImage(id = "7", downloadUrl = "https://picsum.photos/id/35/600"),
        GalleryImage(id = "8", downloadUrl = "https://picsum.photos/id/36/600"),
        GalleryImage(id = "9", downloadUrl = "https://picsum.photos/id/37/600"),
        GalleryImage(id = "10", downloadUrl = "https://picsum.photos/id/38/600")
    )
    StoreLabTestAppTheme {
        DisplayImageGallery(galleryImages)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDisplayImage() {
    val image = GalleryImage(id = "1", downloadUrl = "https://picsum.photos/id/29/600")
    StoreLabTestAppTheme {
        DisplayGalleryImage(image)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPageButtons() {
    StoreLabTestAppTheme() {
        var pageNo by remember {
            mutableStateOf(1)
        }
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
}