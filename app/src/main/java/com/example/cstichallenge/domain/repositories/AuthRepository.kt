package com.example.cstichallenge.domain.repositories

import com.example.cstichallenge.core.utils.ResourceEvent
import com.example.cstichallenge.data.remote.request.AuthRequestDto
import com.example.cstichallenge.data.remote.response.ListUsersResponseDto
import com.example.cstichallenge.data.remote.response.UserDataResponse

interface AuthRepository {
    //Suspend fun para el login con el body de AuthRequestDto la api reqres.in
    suspend fun loginUserApi(loginRequest: AuthRequestDto): ResourceEvent<Unit>
    //Suspend fun para el registro con el body de AuthRequestDto la api reqres.in
    suspend fun registerUserApi(registerRequest: AuthRequestDto): ResourceEvent<Unit>

    suspend fun getUserListApi(index : Int): ResourceEvent<ListUsersResponseDto>

    suspend fun getAuthToken() : String?

    suspend fun clearAuthToken()
}