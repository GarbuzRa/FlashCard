package com.example.flashcard.data.remote

import com.example.flashcard.domain.model.UserDomain
import com.example.flashcard.domain.model.UserResponceDomain
import com.example.flashcard.domain.repository.RegisterUserRepository
import retrofit2.Call

class RegisterUserRepositoryImpl(val api: ServerApi): RegisterUserRepository {

    override fun registerUser(user: UserDomain): Call<UserResponceDomain> {
       return api.registerUser(user.toData())
    }

}

fun UserResponce.toDomain() = UserResponceDomain(this.id, this.email)
fun UserResponceDomain.toData() = UserResponce(this.id, this.email)

fun User.toDomain() = UserDomain(this.email, this.password)
fun UserDomain.toData() = User(this.email, this.password)