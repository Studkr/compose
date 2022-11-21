package com.cinema.ui.preview

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIconDefaults.Text
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cinema.ui.theme.CinemaTheme
import org.w3c.dom.Text

@Composable
fun WelcomeScreen(){
    val visible by remember { mutableStateOf(true) }
    val density = LocalDensity.current
    AnimatedVisibility(visible = visible, enter = slideInVertically {
        with(density){-100.dp.roundToPx()}
    } + expandVertically(
        expandFrom = Alignment.Top
    ) , exit = slideOutVertically()+ shrinkVertically() + fadeOut()) {

        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Hello",
                Modifier
                    .fillMaxWidth()
                    .height(200.dp))
        }

    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CinemaTheme {
        WelcomeScreen()
    }
}
