package com.example.cstichallenge.di

import com.example.cstichallenge.data.local.store.AuthPreferences
import com.example.cstichallenge.data.remote.services.AuthApiService
import com.example.cstichallenge.data.repositories.AuthRepositoryImpl
import com.example.cstichallenge.domain.repositories.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    //Se provee el AuthRepository para poder usarlo en el proyecto
    @Provides
    @Singleton
    fun providesAuthRepository(apiService: AuthApiService, preferences: AuthPreferences): AuthRepository {
        return AuthRepositoryImpl(apiService, preferences)
    }
}