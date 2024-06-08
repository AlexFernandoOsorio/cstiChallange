package com.example.cstichallenge.domain.usecases

import com.example.cstichallenge.domain.repositories.AuthRepository
import com.example.cstichallenge.domain.models.AuthResultModel
import com.example.cstichallenge.core.utils.ResourceEvent
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class RegisterAuthUseCaseTest {

    private lateinit var authRepository: AuthRepository
    private lateinit var registerAuthUseCase: RegisterAuthUseCase

    @Before
    fun setup() {
        authRepository = mockk(relaxed = true)
        registerAuthUseCase = RegisterAuthUseCase(authRepository)
    }

    @Test
    fun `test invoke returns expected value`() = runBlocking {
        // Given
        val email = "test@test.com"
        val password = "password"
        val expectedAuthResult = AuthResultModel(result = ResourceEvent.Success(Unit))
        coEvery { authRepository.registerUserApi(any()) } returns ResourceEvent.Success(Unit)

        // When
        val actualAuthResult = registerAuthUseCase.invoke(email, password)

        // Then
        assertTrue(actualAuthResult.result is ResourceEvent.Success)
        assertEquals(Unit, (actualAuthResult.result as ResourceEvent.Success).data)
    }
}