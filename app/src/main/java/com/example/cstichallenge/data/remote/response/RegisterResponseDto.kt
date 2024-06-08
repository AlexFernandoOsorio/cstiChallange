package com.example.cstichallenge.data.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterResponseDto(
    @SerializedName("id") val id: String,
    @SerializedName("token") val token: String
)