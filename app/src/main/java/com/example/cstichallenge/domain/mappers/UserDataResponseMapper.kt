package com.example.cstichallenge.domain.mappers

import com.example.cstichallenge.data.remote.response.ListUsersResponseDto
import com.example.cstichallenge.data.remote.response.UserDataResponse
import com.example.cstichallenge.domain.models.UserDataModel

fun UserDataResponse.toUserDataModel(): UserDataModel {
    return UserDataModel(
        id = this.id,
        email = this.email,
        firstName = this.firstName,
        lastName = this.lastName,
        avatar = this.avatar
    )
}