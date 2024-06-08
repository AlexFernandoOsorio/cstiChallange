package com.example.cstichallenge.domain.models

import com.example.cstichallenge.core.utils.ResourceEvent

data class AuthResultModel(
    val passwordError: String? = null,
    val emailError : String? = null,
    val result: ResourceEvent<Unit>? = null
)