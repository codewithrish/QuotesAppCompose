package com.codewithrish.composepractive.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.codewithrish.composepractive.models.Quote

@Composable
fun QuoteList(quotes: Array<Quote>, onClick: (quote: Quote) -> Unit) {
    LazyColumn(content = {
        items(quotes) {
            QuoteLiteItem(quote = it, onClick)
        }
    })
}