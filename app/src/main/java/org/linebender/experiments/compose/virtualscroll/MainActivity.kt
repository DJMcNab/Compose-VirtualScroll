package org.linebender.experiments.compose.virtualscroll

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameMillis
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.DeviceFontFamilyName
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.FontScaling
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.linebender.experiments.compose.virtualscroll.ui.theme.ComposeVirtualScrollTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeVirtualScrollTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val listState = rememberLazyListState()
//    val font_size = TextUnit(8f, TextUnitType.Sp)
    val font_size = with(LocalDensity.current) { 8.dp.toSp() }
    LaunchedEffect(Unit) {
        while (true) {
            val frame_time = withFrameNanos { it }
            listState.scrollBy(599f)
        }
    }
//    Log.e("Test", "$sizeInPx")
    Column {
        // If we allow the user to scroll, for some reason that blocks the scrollBy forever
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(0.dp),
            userScrollEnabled = false
        ) {
            items(100000000) { index ->
                Text(
                    "$index: Rust UI and Jetpack Compose Virtual Scrolling Comparison - this text at 8pt just fits the width of my Google Pixel 6.",
//                    fontWeight = FontWeight((index) % 500 + 1),
                    overflow = TextOverflow.Visible,
                    fontFamily = FontFamily(Font(DeviceFontFamilyName("Roboto"))),
                    fontSize = font_size,
                    lineHeight = font_size
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeVirtualScrollTheme {
        Greeting("Android")
    }
}