package com.codewithrish.composepractive

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.codewithrish.composepractive.models.Quote
import com.google.gson.Gson

object DataManager  {

    var data = emptyArray<Quote>()
    var currentQuote: Quote? = null

    var currentPage by mutableStateOf(Pages.LISTING)
    var isDataLoaded by mutableStateOf(false)

    fun loadAssetsFromFile(context: Context ) {
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer,  Charsets.UTF_8)
        val gson = Gson()
        data = gson.fromJson(json, Array<Quote>::class.java)
        isDataLoaded = true
    }

    fun switchPages(quote: Quote?) {
        currentQuote = quote
        currentPage = if (currentPage == Pages.LISTING) {
            Pages.DETAIL
        } else {
            Pages.LISTING
        }
    }
}