package com.example.cstichallenge.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponseDto(
    @SerializedName("token") val token: String
)