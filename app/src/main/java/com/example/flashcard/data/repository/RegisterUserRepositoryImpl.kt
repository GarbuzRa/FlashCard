package com.example.flashcard.data.repository

import com.example.flashcard.data.remote.ServerApi
import com.example.flashcard.data.remote.User
import com.example.flashcard.data.remote.UserResponce
import com.example.flashcard.domain.model.UserDomain
import com.example.flashcard.domain.model.UserResponceDomain
import com.example.flashcard.domain.repository.RegisterUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class RegisterUserRepositoryImpl(val api: ServerApi): RegisterUserRepository {

    override suspend fun registerUser(user: UserDomain): Result<UserResponceDomain> = withContext(Dispatchers.IO) {
        /*
        Нашу функцию необходимо обернуть в Result, потому что таким образом мы обрабатываем ошибки (или их отсутствие) связанные
        с получением информации с сервера - это делается в блоке try-catch.

        Первая проверка
        */
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