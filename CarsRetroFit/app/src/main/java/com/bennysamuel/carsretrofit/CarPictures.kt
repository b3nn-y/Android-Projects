package com.bennysamuel.carsretrofit

import com.google.gson.annotations.SerializedName

data class CarPictures(
    @SerializedName("albumId")
    val albumID: Int,
    @SerializedName("id")
    val id : Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("url")
    val url:String,
    @SerializedName("thumbnailUrl")
    val thumbUrl: String

) {
}