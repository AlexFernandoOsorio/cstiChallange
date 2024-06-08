package com.example.cstichallenge.data.remote.response


import com.google.gson.annotations.SerializedName

data class SupportResponse(
    @SerializedName("text") val text: String,
    @SerializedName("url") val url: String
)