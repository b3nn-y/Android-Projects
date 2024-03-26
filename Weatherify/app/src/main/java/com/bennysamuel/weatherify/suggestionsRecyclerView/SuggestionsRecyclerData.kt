package com.bennysamuel.weatherify.suggestionsRecyclerView

import com.bennysamuel.weatherify.suggestionsRetrofit.Feature

data class SuggestionsRecyclerData(
    var suggestionsText:String,
    var searchTerm: String
) {
}