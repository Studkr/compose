package com.cinema.ui.view.preview

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cinema.R
import com.cinema.compose.TextTitle
import com.cinema.navigation.NavRouters

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PreviewView(
    viewModel: PreviewViewModel,
    navController: NavController
) {
    val state = viewModel.uiState.collectAsState()
    Scaffold(
        backgroundColor = Color.Black
    ) {
       
            when (state.value) {
                is PreviewContract.PreviewScreenState.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LoadingCircleAnimation()
                        TextTitle(text = stringResource(id = R.string.loading))
                    }
                }
                is PreviewContract.PreviewScreenState.Finish -> {
                    navController.navigate(NavRouters.Home.route){
                        launchSingleTop = true
                    }
                }
            }
        }
    }

@Composable
fun LoadingCircleAnimation(
    circleColor: Color = Color.Yellow,
    animationDelay: Int = 1000
) {

    var circleScale by remember {
        mutableStateOf(0f)
    }
    val circleScaleAnimate = animateFloatAsState(
        targetValue = circleScale,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationDelay
            )
        )
    )

    LaunchedEffect(Unit) {
        circleScale = 1f
    }

    Box(
        modifier = Modifier
            .size(size = 64.dp)
            .scale(scale = circleScaleAnimate.value)
            .border(
                width = 8.dp,
                color = circleColor.copy(alpha = 1 - circleScaleAnimate.value),
                shape = CircleShape
            )
    ) {

    }
}