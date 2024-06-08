package com.example.cstichallenge.data.repositories

import com.example.cstichallenge.core.utils.ResourceEvent
import com.example.cstichallenge.data.local.store.AuthPreferences
import com.example.cstichallenge.data.remote.request.AuthRequestDto
import com.example.cstichallenge.data.remote.response.ListUsersResponseDto
import com.example.cstichallenge.data.remote.response.UserDataResponse
import com.example.cstichallenge.data.remote.services.AuthApiService
import com.example.cstichallenge.domain.repositories.AuthRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi : AuthApiService,
    private val preferences : AuthPreferences
) : AuthRepository {

    // Suspend fun para el login con el body de AuthRequestDto la api reqres.in
    override suspend fun loginUserApi(loginRequest: AuthRequestDto) :ResourceEvent<Unit>{
        return try {
            //Se realiza la petición POST para el login con el body de AuthRequestDto
            val response = authApi.loginUser(loginRequest)
            //Se guarda el token en el AuthPreferences caso de que la petición sea exitosa
            preferences.saveAuthToken(response.token)
            ResourceEvent.Success(Unit)
        }catch (e: IOException){
            //En caso de que la petición no sea exitosa se retorna un ResourceEvent.Error
            ResourceEvent.Error("${e.message}")
        }catch (e: HttpException){
            //En caso de que la petición no sea exitosa se retorna un ResourceEvent.Error
            ResourceEvent.Error("${e.message}")
        }
    }

    //Suspend fun para el registro con el body de AuthRequestDto la api reqres.in
    override suspend fun registerUserApi(registerRequest: AuthRequestDto): ResourceEvent<Unit> {
        return try {
            //Se realiza la petición POST para el registro con el body de AuthRequestDto
            val response = authApi.registerUser(registerRequest)
            //Se guarda el token en el AuthPreferences caso de que la petición sea exitosa
            preferences.saveAuthToken(response.token)
            ResourceEvent.Success(Unit)
        }catch (e: IOException){
            //En caso de que la petición no sea exitosa se retorna un ResourceEvent.Error
            ResourceEvent.Error("${e.message}")
        }catch (e: HttpException){
            //En caso de que la petición no sea exitosa se retorna un ResourceEvent.Error
            ResourceEvent.Error("${e.message}")
        }
    }

    //Suspend fun para obtener la lista de usuarios de la api reqres.in
    override suspend fun getUserListApi(index : Int): ResourceEvent<ListUsersResponseDto> {
        return try {
            val userResponse = authApi.getUsersList(index)
            ResourceEvent.Success(userResponse)
        }catch (e: IOException){
            //En caso de que la petición no sea exitosa se retorna un ResourceEvent.Error
            ResourceEvent.Error("${e.message}")
        }catch (e: HttpException){
            //En caso de que la petición no sea exitosa se retorna un ResourceEvent.Error
            ResourceEvent.Error("${e.message}")
        }
    }
}