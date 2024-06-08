package com.example.cstichallenge.data.models

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("data")
    var data: DataDto? = DataDto(),
    @SerializedName("support")
    var support: SupportDto? = SupportDto()
)
