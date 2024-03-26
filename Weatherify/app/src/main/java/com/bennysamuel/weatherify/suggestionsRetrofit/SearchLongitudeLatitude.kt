package com.bennysamuel.weatherify.suggestionsRetrofit

data class SearchedLocation(
    val type: String,
    val features: List<Feature2>,
    val query: Query2
)

data class Feature2(
    val type: String,
    val properties: Properties2,
    val geometry: Geometry2,
    val bbox: List<Double>
)

data class Properties2(
    val datasource: Datasource2,
    val name: String,
    val country: String,
    val country_code: String,
    val state: String,
    val city: String,
    val postcode: String,
    val district: String,
    val suburb: String,
    val street: String,
    val housenumber: String,
    val lon: Double,
    val lat: Double,
    val distance: String,
    val result_type: String,
    val formatted: String,
    val address_line1: String,
    val address_line2: String,
    val category: String,
    val timezone: Timezone2,
    val plus_code: String,
    val rank: Rank2,
    val place_id: String
)

data class Datasource2(
    val sourcename: String,
    val attribution: String,
    val license: String,
    val url: String
)

data class Timezone2(
    val name: String,
    val offset_STD: String,
    val offset_STD_seconds: Int,
    val offset_DST: String,
    val offset_DST_seconds: Int,
    val abbreviation_STD: String,
    val abbreviation_DST: String
)

data class Rank2(
    val importance: Double,
    val popularity: Double
)

data class Geometry2(
    val type: String,
    val coordinates: List<Double>
)

data class Query2(
    val lat: Double,
    val lon: Double,
    val plus_code: String
)
