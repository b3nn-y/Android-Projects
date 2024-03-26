package com.bennysamuel.weatherify.suggestionsRetrofit

data class FeatureCollection(
    val type: String,
    val features: List<Feature>,
    val query: Query
)

data class Feature(
    val type: String,
    val properties: Properties,
    val geometry: Geometry,
    val bbox: List<Double>
)

data class Properties(
    val datasource: DataSource,
    val name: String,
    val country: String,
    val country_code: String,
    val state: String,
    val state_district: String,
    val county: String,
    val postcode: String,
    val suburb: String,
    val lon: Double,
    val lat: Double,
    val state_code: String,
    val district: String,
    val city: String,
    val hamlet: String,
    val category: String,
    val timezone: Timezone,
    val plus_code: String,
    val rank: Rank,
    val place_id: String,
    val ref: String,
    val street: String
)

data class DataSource(
    val sourcename: String,
    val attribution: String,
    val license: String,
    val url: String
)

data class Timezone(
    val name: String,
    val offset_STD: String,
    val offset_STD_seconds: Int,
    val offset_DST: String,
    val offset_DST_seconds: Int,
    val abbreviation_STD: String,
    val abbreviation_DST: String
)

data class Rank(
    val importance: Double,
    val confidence: Double,
    val confidence_city_level: Double,
    val match_type: String
)

data class Geometry(
    val type: String,
    val coordinates: List<Double>
)

data class Query(
    val text: String
)
