package com.example.flashcard.domain.repository

interface NotificationRepository {

    fun saveToken(token: String)

    fun getToken(): String?
}