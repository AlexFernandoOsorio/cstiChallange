package com.example.cstichallenge.domain.usecases

import com.example.cstichallenge.data.remote.request.AuthRequestDto
import com.example.cstichallenge.domain.models.AuthResultModel
import com.example.cstichallenge.domain.repositories.AuthRepository
import javax.inject.Inject

class RegisterAuthUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    //Suspend fun para el registro con el body de AuthRequestDto la api reqres.in
    suspend operator fun invoke(email:String, password:String) : AuthResultModel {

        val emailError = if (email.isBlank()) "Username cannot be blank" else null
        val passwordError = if (password.isBlank()) "Password cannot be blank" else null

        //Se valida que el email y el password no estén vacíos
        if (emailError != null) {
            return AuthResultModel(
                emailError = emailError
            )
        }
        if (passwordError != null) {
            return AuthResultModel(
                passwordError = passwordError
            )
        }
        //Se crea el body de AuthRequestDto
        val loginRequest = AuthRequestDto(
            email = email.trim(),
            password = password.trim()
        )
        //Se retorna el resultado de la petición realizando una llamada al repositorio
        return AuthResultModel(
            result = repository.registerUserApi(loginRequest)
        )
    }
}