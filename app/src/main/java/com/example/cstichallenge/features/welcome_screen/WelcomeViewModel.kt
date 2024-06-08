package com.example.cstichallenge.features.welcome_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.cstichallenge.domain.models.UserDataModel
import com.example.cstichallenge.domain.usecases.GetUserListUseCase
import com.example.cstichallenge.features.AuthState
import com.example.cstichallenge.features.common.TextFieldState
import com.example.cstichallenge.features.navigation.AppScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase
): ViewModel(){

    //Se inicializa el estado de la pantalla de registro
    private var _verifyState  = mutableStateOf(AuthState())
    val verifyState: State<AuthState> = _verifyState

    //Se inicializa el estado del email
    private val _emailState = mutableStateOf(TextFieldState())
    val emailState: State<TextFieldState> = _emailState

    fun setEmail(value:String){
        _emailState.value = emailState.value.copy(text = value)
    }

    //Se valida que el email no esté vacío
    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    //Se asigna el evalor de error al estado de email
    fun setEmailError(value:String){
        _emailState.value = emailState.value.copy(error = "Invalid Email")
    }

    fun checkEmailInAPI(email: String, navController : NavController){
        // Corutina que realiza la petición de login
        viewModelScope.launch {
            // Se realiza la petición de login con el email y el password al useCase
            val loginResult = getUserListUseCase()
            for (user in loginResult){
                if (user.email == email){
                    val userArgument = getUserArgument(user)
                    navController.navigate(route = AppScreens.LoginScreen.route + "/${userArgument}")
                }
            }
        }
    }

    private fun getUserArgument(user : UserDataModel): String {
        val email = user.email
        val avatar = user.avatar.toString().replace("/", "^")
        val name = (user.firstName + " " + user.lastName).replace("/", "^")
        return "$email +|+ $avatar +|+ $name"
    }
}