package com.example.flashcard.domain.usecase

import com.example.flashcard.domain.repository.NotificationRepository

class NotificationInteractor(val notificationRepository: NotificationRepository) {


    fun saveToken(token: String) {
        notificationRepository.saveToken(token)
    }

    fun getToken(): String? {
        return notificationRepository.getToken()
    }
}