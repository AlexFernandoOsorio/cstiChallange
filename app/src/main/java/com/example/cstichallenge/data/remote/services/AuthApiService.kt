package com.example.cstichallenge.data.remote.services

import com.example.cstichallenge.core.utils.Constants.END_POINT_LOGIN
import com.example.cstichallenge.core.utils.Constants.END_POINT_REGISTER
import com.example.cstichallenge.core.utils.Constants.END_POINT_RESOURCE
import com.example.cstichallenge.data.remote.request.AuthRequestDto
import com.example.cstichallenge.data.remote.response.ListUsersResponseDto
import com.example.cstichallenge.data.remote.response.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApiService {

    //Se realiza la peticion POST para el login con el body de AuthRequestDto
    @POST(END_POINT_LOGIN)
    suspend fun loginUser(@Body loginRequest: AuthRequestDto) : LoginResponseDto

    //Se realiza la peticion POST para el registro con el body de AuthRequestDto
    @POST(END_POINT_REGISTER)
    suspend fun registerUser(@Body registerRequest: AuthRequestDto) : LoginResponseDto

    //Se realiza la peticion GET para obtener los datos de una lista de recursos
    @GET(END_POINT_RESOURCE)
    suspend fun getUsersList(@Query("page") page: Int) : ListUsersResponseDto

}