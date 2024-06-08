package com.example.cstichallenge.domain.usecases

import com.example.cstichallenge.domain.repositories.AuthRepository
import com.example.cstichallenge.core.utils.ResourceEvent
import com.example.cstichallenge.data.remote.response.UserDataResponse
import com.example.cstichallenge.domain.mappers.toUserDataModel
import com.example.cstichallenge.domain.models.UserDataModel
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    // Suspend fun para obtener la lista de usuarios de la api reqres.in
    suspend operator fun invoke() : List<UserDataModel> {
        val users = mutableListOf<UserDataResponse>()
        var currentPage = 1
        var totalPages: Int
        //Se realiza la petición GET para obtener los datos de una lista de recursos
        do {
            when (val userResponse = repository.getUserListApi(currentPage)) {
                is ResourceEvent.Success -> {
                    val userDataResponse = userResponse.data?.data
                    for (i in 0 until userDataResponse?.size!!){
                        users.add(userDataResponse[i])
                    }
                    totalPages = userResponse.data.totalPages
                    currentPage++
                }
                is ResourceEvent.Error -> {
                    //En caso de que la petición no sea exitosa se retorna un ResourceEvent.Error
                    return emptyList()
                }
                is ResourceEvent.Loading -> totalPages = 0
            }
        } while (currentPage <= totalPages)
        // Convertir UserDataResponse a UserDataModel
        return users.map { it.toUserDataModel() }
    }
}
