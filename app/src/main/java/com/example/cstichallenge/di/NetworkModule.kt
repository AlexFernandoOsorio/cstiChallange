package com.example.cstichallenge.di

import com.example.cstichallenge.core.utils.Constants.BASE_URL
import com.example.cstichallenge.data.remote.services.AuthApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    //Para poder usar el servicio de AuthApiService en el proyecto
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): AuthApiService {
        return provideRetrofit().create(AuthApiService::class.java)
    }

}