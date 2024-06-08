package com.example.cstichallenge.domain.usecases

import com.example.cstichallenge.domain.repositories.AuthRepository
import javax.inject.Inject
class GetAuthTokenUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    // Suspend fun para obtener el token de la api reqres.in localmente
    suspend operator fun invoke(): String? {
        return repository.getAuthToken()
    }
}