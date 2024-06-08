package com.example.cstichallenge.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cstichallenge.features.home_screen.HomeScreen
import com.example.cstichallenge.features.login_screen.LoginScreen
import com.example.cstichallenge.features.register_screen.RegisterScreen
import com.example.cstichallenge.features.welcome_screen.WelcomeScreen

@Composable
fun AppNavigation () {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =  AppScreens.WelcomeScreen.route){
        composable(route = AppScreens.WelcomeScreen.route){
            WelcomeScreen(navController)
        }
        //Se envía el email como argumento
        composable(route = AppScreens.LoginScreen.route + "/{user}",
            arguments = listOf(navArgument("user") {
                type = NavType.StringType
            })
        ) {
            //Se recibe el user como argumento
            LoginScreen(navController,it.arguments?.getString("user"))
        }
        composable(route = AppScreens.RegisterScreen.route){
            RegisterScreen(navController)
        }
        composable(route = AppScreens.HomeScreen.route){
            HomeScreen(navController)
        }
    }
}