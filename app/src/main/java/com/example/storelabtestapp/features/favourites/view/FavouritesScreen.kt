package com.example.storelabtestapp.features.favourites.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.storelabtestapp.common.theme.StoreLabTestAppTheme
import com.example.storelabtestapp.data.model.fullscreenimage.FavouriteImage

@Composable
fun DisplayFavouriteImages(images: List<FavouriteImage>) {
    if (images.isNotEmpty()) {
        LazyColumn() {
            items(
                items = images,
                itemContent = { item ->
                    DisplayFavouriteImage(item)
                }
            )
        }
    }
}

@Composable
fun DisplayFavouriteImage(image: FavouriteImage) {
    Card(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = image.imageUrl,
                contentDescription = image.imageId,
                contentScale = ContentScale.FillHeight
            )
        }
    }
}

@Composable
fun DisplayHeading() {
    Text(
        text = "Favouites",
        textAlign = TextAlign.Center,
        fontSize = 26.sp,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDisplayFavouriteImage() {
    val image = FavouriteImage(imageUrl = "https://picsum.photos/id/29/300/300.jpg", imageId = "29")
    StoreLabTestAppTheme() {
        DisplayFavouriteImage(image = image)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDisplayFavouriteImages() {
    val images = listOf(
        FavouriteImage(imageUrl = "https://picsum.photos/id/29/300/300.jpg"),
        FavouriteImage(imageUrl = "https://picsum.photos/id/30/300/300.jpg"),
        FavouriteImage(imageUrl = "https://picsum.photos/id/31/300/300.jpg"),
        FavouriteImage(imageUrl = "https://picsum.photos/id/32/300/300.jpg"),
        FavouriteImage(imageUrl = "https://picsum.photos/id/33/300/300.jpg"),
        FavouriteImage(imageUrl = "https://picsum.photos/id/34/300/300.jpg"),
        FavouriteImage(imageUrl = "https://picsum.photos/id/35/300/300.jpg"),
        FavouriteImage(imageUrl = "https://picsum.photos/id/36/300/300.jpg"),
        FavouriteImage(imageUrl = "https://picsum.photos/id/37/300/300.jpg"),
        FavouriteImage(imageUrl = "https://picsum.photos/id/38/300/300.jpg")
    )
    StoreLabTestAppTheme() {
        DisplayFavouriteImages(images = images)
    }
}