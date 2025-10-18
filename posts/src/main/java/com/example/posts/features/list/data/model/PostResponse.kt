package com.example.posts.features.list.data.model

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String
)
