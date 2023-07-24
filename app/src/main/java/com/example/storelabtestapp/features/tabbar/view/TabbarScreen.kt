package com.example.storelabtestapp.features.tabbar.view

import android.content.ContextWrapper
import android.content.Intent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.storelabtestapp.common.theme.StoreLabTestAppTheme
import com.example.storelabtestapp.features.favourites.FavouritesActivity
import com.example.storelabtestapp.features.home.MainActivity

@Composable
fun DisplayTabBar(selectedTabIndex: Int) {
    val context = LocalContext.current
    val titlesOfTabs = listOf(
        "Gallery",
        "Favourites"
    )
    val activitiesOfTabs = listOf(
        MainActivity::class.java,
        FavouritesActivity::class.java
    )
    TabRow(
        selectedTabIndex = selectedTabIndex,
        tabs = {
            titlesOfTabs.forEach { titleOfTab ->
                val isTabSelected = titlesOfTabs[selectedTabIndex] == titleOfTab
                Tab(
                    selected = isTabSelected,
                    onClick = {
                        when (isTabSelected) {
                            true -> {}
                            false -> {
                                val intent = Intent(context, activitiesOfTabs[titlesOfTabs.indexOf(titleOfTab)])
                                ContextWrapper(context).startActivity(intent)
                            }
                        }
                    },
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(
                                size = 1.dp
                            )
                        ),
                    enabled = !isTabSelected,
                    selectedContentColor = Color.Gray,
                    unselectedContentColor = Color.Blue
                ) {
                    Text(
                        text = titleOfTab,
                        fontSize = 26.sp,
                        fontWeight = when (isTabSelected) {
                            true -> FontWeight.Bold
                            false -> FontWeight.Normal
                        },
                        textDecoration = when (isTabSelected) {
                            true -> TextDecoration.Underline
                            false -> TextDecoration.None
                        }
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDisplayTabBar() {
    StoreLabTestAppTheme {
        DisplayTabBar(0)
    }
}