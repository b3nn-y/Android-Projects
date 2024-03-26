package com.bennysamuel.mememania

import com.google.gson.annotations.SerializedName

data class MemesData(
    @SerializedName("count")
    val count: Int,
    @SerializedName("memes")
    val memes: List<Memes>
)

data class Memes(
    @SerializedName("postLink")
    val postLink: String,
    @SerializedName("subreddit")
    val subreddit: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,

)

