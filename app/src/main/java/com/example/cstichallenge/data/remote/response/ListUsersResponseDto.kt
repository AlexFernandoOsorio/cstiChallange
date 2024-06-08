package com.example.cstichallenge.data.remote.response


import com.google.gson.annotations.SerializedName

data class ListUsersResponseDto(
    @SerializedName("data") val `data`: List<UserDataResponse>,
    @SerializedName("page") val page: Int,
    @SerializedName("per_page") val perPage: Int,
    @SerializedName("support") val support: SupportResponse,
    @SerializedName("total") val total: Int,
    @SerializedName("total_pages") val totalPages: Int
)