package com.example.cstichallenge.data.models

import com.google.gson.annotations.SerializedName

data class SupportDto(
    @SerializedName("url"  )
    var url  : String? = null,
    @SerializedName("text" )
    var text : String? = null
)
