package com.example.flashcard.domain.repository

import com.example.flashcard.domain.model.UserDomain
import com.example.flashcard.domain.model.UserResponceDomain
import retrofit2.Call

interface RegisterUserRepository {
    fun registerUser(user: UserDomain): Call<UserResponceDomain>
}