package com.example.flashcard.domain.repository

import com.example.flashcard.domain.model.UserDomain
import com.example.flashcard.domain.model.UserResponceDomain
import retrofit2.Call

interface RegisterUserRepository {
   suspend fun registerUser(user: UserDomain): Result<UserResponceDomain>
}