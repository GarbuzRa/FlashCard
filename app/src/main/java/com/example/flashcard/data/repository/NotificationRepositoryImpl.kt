package com.example.flashcard.data.repository

import android.content.SharedPreferences
import com.example.flashcard.data.notification.FirebaseNotification
import com.example.flashcard.domain.repository.NotificationRepository

class NotificationRepositoryImpl(val notificationSp: SharedPreferences): NotificationRepository {
    override fun saveToken(token: String) {
        notificationSp.edit().putString(TOKEN_KEY, token).apply()
    }

    override fun getToken(): String? {
        return notificationSp.getString(TOKEN_KEY, null)
    }

    companion object {
        const val TOKEN_KEY = "DeviceToken"
    }

}