package com.example.flashcard.data.remote

import com.example.flashcard.domain.model.UserDomain
import com.example.flashcard.domain.model.UserResponceDomain
import com.example.flashcard.domain.repository.RegisterUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.HttpException

class RegisterUserRepositoryImpl(val api: ServerApi): RegisterUserRepository {

    override suspend fun registerUser(user: UserDomain): Result<UserResponceDomain> = withContext(Dispatchers.IO) {
      try {
          val responce = api.registerUser(user.toData()).execute()
          if (responce.isSuccessful) {
              val body = responce.body()
              if (body != null) {
                  Result.success(body.toDomain())
              } else {
                  Result.failure(NullPointerException("Тело пустое"))
              }
          } else {
              Result.failure(HttpException(responce))
          }
      } catch (e: Exception) {
          Result.failure(e)
      }
    }
}

fun UserResponce.toDomain() = UserResponceDomain(this.id, this.email)
fun UserResponceDomain.toData() = UserResponce(this.id, this.email)

fun User.toDomain() = UserDomain(this.email, this.password)
fun UserDomain.toData() = User(this.email, this.password)