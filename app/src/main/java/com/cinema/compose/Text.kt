package com.cinema.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TextTitle(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(start = 8.dp, top = 8.dp),
        fontSize = 21.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
}

@Composable
fun TextMiddle(text: String, top: Dp = 0.dp, bottom: Dp = 0.dp, start: Dp = 0.dp,end: Dp = 0.dp) {
    Text(
        text = text,
        modifier = Modifier.padding(start = start!!, top = bottom!!, end = end!!,bottom = bottom),
        fontSize = 16.sp,
        color = Color.White
    )
}

@Composable
fun TextMiddleWithPadding(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(start = 12.dp, top = 8.dp, end = 12.dp, bottom = 8.dp),
        fontSize = 16.sp,
        color = Color.Black
    )
}
