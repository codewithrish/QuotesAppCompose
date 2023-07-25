package com.codewithrish.composepractive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.codewithrish.composepractive.screens.QuoteDetail
import com.codewithrish.composepractive.screens.QuoteListScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect(key1 = "", block = {
                CoroutineScope(Dispatchers.IO).launch {
                    delay(300)
                    DataManager.loadAssetsFromFile(applicationContext)
                }
            })
            App()
        }
    }
}

@Composable
fun App() {
    if (DataManager.isDataLoaded) {
        if (DataManager.currentPage == Pages.LISTING) {
            QuoteListScreen(quotes = DataManager.data) {
                DataManager.switchPages(it)
            }
        } else {
            DataManager.currentQuote?.let { QuoteDetail(quote = it) }
        }
    } else {
         Box(
             contentAlignment = Alignment.Center,
             modifier = Modifier.fillMaxSize()
         ) {
             Text(text = "Loading ...", style = MaterialTheme.typography.titleMedium)
         }
    }
}

enum class Pages {
    LISTING,
    DETAIL
}