package com.example.cstichallenge.features.home_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cstichallenge.domain.usecases.GetAuthTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAuthTokenUseCase: GetAuthTokenUseCase
): ViewModel(){

    //Se inicializa el estado del password
    private val _tokenState = mutableStateOf(String())
    val tokenState: MutableState<String> = _tokenState

    // Suspend fun para obtener el token de la api reqres.in localmente
    fun getAuthToken() {
        viewModelScope.launch {
            val token = getAuthTokenUseCase()
            _tokenState.value = token.toString()
        }
    }
}