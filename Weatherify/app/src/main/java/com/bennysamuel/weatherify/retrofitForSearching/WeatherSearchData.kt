package com.bennysamuel.weatherify.retrofitForSearching

import com.google.gson.annotations.SerializedName

data class WeatherData(
    @SerializedName("time")
    val time: String,
    @SerializedName("values")
    val values: WeatherValues,
    @SerializedName("location")
    val location: WeatherLocation
)

data class WeatherValues(
    @SerializedName("cloudCover")
    val cloudCover: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("temperature")
    val temperature: Int,
    @SerializedName("uvIndex")
    val uvIndex: Int,
    @SerializedName("windSpeed")
    val windSpeed: Double
)

data class WeatherLocation(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String
)