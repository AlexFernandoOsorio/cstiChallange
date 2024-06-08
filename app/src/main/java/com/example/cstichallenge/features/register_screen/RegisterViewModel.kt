package com.example.cstichallenge.features.register_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cstichallenge.features.common.TextFieldState
import com.example.cstichallenge.features.common.UiState
import com.example.cstichallenge.core.utils.ResourceEvent
import com.example.cstichallenge.domain.usecases.RegisterAuthUseCase
import com.example.cstichallenge.features.AuthState
import com.example.cstichallenge.features.navigation.AppScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterAuthUseCase
): ViewModel(){
    //Se inicializa el estado de la pantalla de registro
    private var _loginState  = mutableStateOf(AuthState())
    val loginState: State<AuthState> = _loginState
    //Se inicializa el evento de la pantalla de registro
    private val  _eventFlow = MutableSharedFlow<UiState>()
    val eventFlow = _eventFlow.asSharedFlow()
    //Se inicializa el estado del email
    private val _emailState = mutableStateOf(TextFieldState())
    val emailState: State<TextFieldState> = _emailState

    fun setEmail(value:String){
        _emailState.value = emailState.value.copy(text = value)
    }
    //Se inicializa el estado del password
    private val _passwordState = mutableStateOf(TextFieldState())
    val passwordState: State<TextFieldState> = _passwordState

    fun setPassword(value:String){
        _passwordState.value = passwordState.value.copy(text = value)
    }

    fun registerUser(){
        //Corutina que realiza la petición de registro
        viewModelScope.launch {
            _loginState.value = loginState.value.copy(isLoading = false)
            //Se realiza la petición de registro con el email y el password al useCase
            val registerResult = registerUseCase(
                email = emailState.value.text,
                password = passwordState.value.text
            )

            _loginState.value = loginState.value.copy(isLoading = false)
            //Se valida que el email y el password no estén vacíos
            if (registerResult.emailError != null){
                _emailState.value=emailState.value.copy(error = registerResult.emailError)
            }
            if (registerResult.passwordError != null){
                _passwordState.value = passwordState.value.copy(error = registerResult.passwordError)
            }
            //Se valida el resultado de la petición
            when(registerResult.result){
                is ResourceEvent.Success->{
                    //En caso de que la petición sea exitosa se navega a la pantalla de Home
                    _eventFlow.emit(
                        UiState.NavigateEvent(AppScreens.HomeScreen.route)
                    )
                }
                is ResourceEvent.Error->{
                    //En caso de que la petición no sea exitosa se muestra un snackbar con el mensaje de error
                    _eventFlow.emit(
                        UiState.SnackbarEvent(
                            registerResult.result.message ?: "Error!"
                        )
                    )
                }
                else -> {

                }
            }
        }
    }
}