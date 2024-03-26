package com.bennysamuel.weatherify.weatherApi

data class Weather(
    val latitude: Double,
    val longitude: Double,
    val generationtime_ms: Double,
    val utc_offset_seconds: Int,
    val timezone: String,
    val timezone_abbreviation: String,
    val elevation: Double,
    val current_units: CurrentUnits,
    val current: CurrentWeather,
    val daily_units: DailyUnits,
    val daily: DailyWeather
)

data class CurrentUnits(
    val time: String,
    val interval: String,
    val temperature_2m: String,
    val relative_humidity_2m: String,
    val is_day: String,
    val precipitation: String,
    val rain: String,
    val showers: String,
    val snowfall: String,
    val cloud_cover: String
)

data class CurrentWeather(
    val time: String,
    val interval: Int,
    val temperature_2m: Double,
    val relative_humidity_2m: Int,
    val is_day: Int,
    val precipitation: Double,
    val rain: Double,
    val showers: Double,
    val snowfall: Double,
    val cloud_cover: Int
)

data class DailyUnits(
    val time: String,
    val sunrise: String,
    val sunset: String,
    val uv_index_max: String
)

data class DailyWeather(
    val time: List<String>,
    val sunrise: List<String>,
    val sunset: List<String>,
    val uv_index_max: List<Double>
)
