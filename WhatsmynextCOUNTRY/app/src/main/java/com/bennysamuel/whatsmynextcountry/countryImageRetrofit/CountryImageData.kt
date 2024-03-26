package com.bennysamuel.whatsmynextcountry.countryImageRetrofit

import com.google.gson.annotations.SerializedName

data class ImageItem(
    @SerializedName("id") val id: Int,
    @SerializedName("pageURL") val pageURL: String,
    @SerializedName("largeImageURL") val largeImageURL: String

)

data class PixabayResponse(
    @SerializedName("total") val total: Int,
    @SerializedName("totalHits") val totalHits: Int,
    @SerializedName("hits") val hits: List<ImageItem>
)