package com.example.flashcard.data.remote

import android.provider.ContactsContract.CommonDataKinds.Email
import com.example.flashcard.BuildConfig
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ServerApi {
    @Headers(
        "Content-Type: application/json",
        "apikey: ${BuildConfig.ACCESS_TOKEN}",
        "Authorization: Bearer ${BuildConfig.ACCESS_TOKEN}"
    )
    @POST("/auth/v1/signup")
   suspend fun registerUser(@Body user: User): Call<UserResponce>
}

data class User(
    val email: String,
    val password: String
)

data class UserResponce(
    val id: String?,
    val email: String?
)