package com.example.flashcard.data.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.flashcard.domain.repository.NotificationRepository
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.koin.android.ext.android.inject

class FirebaseNotification: FirebaseMessagingService() {
    private val repository: NotificationRepository by inject()

    override fun onNewToken(token: String) { //срабатывает при получении нового токена от firebase
        super.onNewToken(token)
        repository.saveToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) { //срабатывает при получении нового сообщения (обратить внимание на RemoteMessage)
        super.onMessageReceived(message)
        message.notification?.let {notification ->
            showNotification(notification.title, notification.body)
        }
    }
    private fun showNotification(title: String?, body: String?) {
        val notificationsManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("default_channel", "Default Channel", NotificationManager.IMPORTANCE_DEFAULT)
            notificationsManager.createNotificationChannel(channel)
        }
        val notification = NotificationCompat.Builder(this, "default_channel")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        notificationsManager.notify(1, notification)
    }
}