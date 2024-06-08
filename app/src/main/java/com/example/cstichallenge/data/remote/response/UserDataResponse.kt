package com.example.cstichallenge.data.remote.response


import com.google.gson.annotations.SerializedName

data class UserDataResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("avatar") val avatar: String,
    @SerializedName("email") val email: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String
)