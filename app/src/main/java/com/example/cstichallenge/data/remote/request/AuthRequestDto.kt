package com.example.cstichallenge.data.remote.request

import com.google.gson.annotations.SerializedName

data class AuthRequestDto(
    @SerializedName("email") var email: String,
    @SerializedName("password") var password: String
)