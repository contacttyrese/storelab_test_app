package com.example.storelabtestapp.features.fullscreenimage.view

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.storelabtestapp.common.theme.StoreLabTestAppTheme

@Composable
fun DisplayFullscreenImage(imageUrl: String) {
    val context = LocalContext.current
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = "fullscreen image",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxSize()
    )

}

@Composable
fun DisplayAddToFavourites(onClick: ()->Unit) {
    Button(
        onClick = onClick,
        shape = CircleShape
    ) {
        Text(
            text = "Add to favourites"
        )

    }
}

@Composable
fun DisplayBackButton() {
    val backPressedCallback = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    IconButton(
        onClick = {
            backPressedCallback?.onBackPressed()
        },
        colors = IconButtonDefaults.outlinedIconButtonColors(
            containerColor = Color.Gray,
            contentColor = Color.Red
        )
    ) {
        Icon(
            Icons.Default.Close,
            contentDescription = "back button"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDisplayFullscreenImage() {
    val url = "https://picsum.photos/id/29/900/1400"

    StoreLabTestAppTheme() {
        DisplayFullscreenImage(imageUrl = url)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDisplayAddToFavourite() {
    StoreLabTestAppTheme() {
        DisplayAddToFavourites { }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDisplayBackButton() {
    StoreLabTestAppTheme {
        DisplayBackButton()
    }
}