package com.example.flashcard.domain.usecase

import com.example.flashcard.domain.model.UserDomain
import com.example.flashcard.domain.model.UserResponceDomain
import com.example.flashcard.domain.repository.RegisterUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call

class RegisterUserUseCase(val registerUserRepository: RegisterUserRepository) {

    suspend operator fun invoke(userDomain: UserDomain): Flow<Result<UserResponceDomain>> = flow {
        try {
            val responce = registerUserRepository.registerUser(userDomain)
            emit(responce)
        } catch (e:Exception) {
            emit(Result.failure(e))
        }
    }
}