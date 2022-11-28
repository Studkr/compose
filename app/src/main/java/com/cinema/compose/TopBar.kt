package com.cinema.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TopAppBar(navController: NavController, @DrawableRes topImage: Int = 0, title:String ="Home") {
    androidx.compose.material.TopAppBar(title = { TextToolBar(title) },
        navigationIcon = {
                Image(
                    painterResource(id = topImage),
                    contentDescription = "",
                    modifier = Modifier
                        .clickable(enabled = true) {
                            navController.popBackStack()
                        }
                        .padding(start = 8.dp, top = 12.dp, bottom = 12.dp)
                )

        }
    )
}