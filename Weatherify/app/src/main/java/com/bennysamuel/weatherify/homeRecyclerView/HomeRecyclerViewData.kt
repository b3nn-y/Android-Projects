package com.bennysamuel.weatherify.homeRecyclerView

data class HomeRecyclerViewData(
    var location:String,
    var searchTerm:String,
    var humidity: String,
    var image: Int,
    var temp: Double
) {
}