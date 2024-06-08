package com.example.cstichallenge.domain.usecases

import com.example.cstichallenge.domain.repositories.AuthRepository
import com.example.cstichallenge.domain.models.AuthResultModel
import com.example.cstichallenge.data.remote.request.AuthRequestDto
import com.example.cstichallenge.core.utils.ResourceEvent
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LoginAuthUseCaseTest {

    private lateinit var authRepository: AuthRepository
    private lateinit var loginAuthUseCase: LoginAuthUseCase

    @Before
    fun setup() {
        authRepository = mockk(relaxed = true)
        loginAuthUseCase = LoginAuthUseCase(authRepository)
    }

    @Test
    fun `test invoke returns expected value`() = runBlocking {
        // Given
        val email = "test@test.com"
        val password = "password"
        val expectedAuthResult = AuthResultModel(result = ResourceEvent.Success(Unit))
        coEvery { authRepository.loginUserApi(any()) } returns ResourceEvent.Success(Unit)

        // When
        val actualAuthResult = loginAuthUseCase.invoke(email, password)

        // Then
        assertTrue(actualAuthResult.result is ResourceEvent.Success)
        assertEquals(Unit, (actualAuthResult.result as ResourceEvent.Success).data)
    }
}