package com.example.cstichallenge.data.repositories

import com.example.cstichallenge.domain.repositories.AuthRepository
import com.example.cstichallenge.data.local.store.AuthPreferences
import com.example.cstichallenge.data.remote.services.AuthApiService
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AuthRepositoryTest {

    private lateinit var authPreferences: AuthPreferences
    private lateinit var authApi: AuthApiService
    private lateinit var authRepository: AuthRepository

    @Before
    fun setup() {
        authPreferences = mockk(relaxed = true)
        authApi = mockk(relaxed = true)
        authRepository = AuthRepositoryImpl(authApi, authPreferences)
    }

    @Test
    fun `test getAuthToken returns expected value`() = runBlocking {
        // Given
        val expectedToken = "testToken"
        coEvery { authPreferences.getAuthToken() } returns expectedToken
        // When
        val actualToken = authRepository.getAuthToken()
        // Then
        assertEquals(expectedToken, actualToken)
    }

    @Test
    fun `test clearAuthToken calls clearAuthToken on AuthPreferences`(): Unit = runBlocking {
        // when
        authRepository.clearAuthToken()
        // then
        coEvery {  authPreferences.clearAuthToken() }
    }
}