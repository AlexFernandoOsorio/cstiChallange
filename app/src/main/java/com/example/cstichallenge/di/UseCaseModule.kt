package com.example.cstichallenge.di

import com.example.cstichallenge.domain.repositories.AuthRepository
import com.example.cstichallenge.domain.usecases.GetUserListUseCase
import com.example.cstichallenge.domain.usecases.LoginAuthUseCase
import com.example.cstichallenge.domain.usecases.RegisterAuthUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    //Se provee el LoginAuthUseCase para poder usarlo en el proyecto
    @Provides
    @Singleton
    fun providesLoginAuthUseCase(repository: AuthRepository): LoginAuthUseCase {
        return LoginAuthUseCase(repository)
    }

    //Se provee el RegisterAuthUseCase para poder usarlo en el proyecto
    @Provides
    @Singleton
    fun providesRegisterAuthUseCase(repository: AuthRepository): RegisterAuthUseCase {
        return RegisterAuthUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesGetUserAuthUseCase(repository: AuthRepository): GetUserListUseCase {
        return GetUserListUseCase(repository)
    }
}