package com.example.flashcard.presentation.viewmodel


import androidx.lifecycle.ViewModel
import com.example.flashcard.domain.usecase.NotificationInteractor

class NotificationViewModel(val interactor: NotificationInteractor): ViewModel()  {

    fun saveToken(token: String) {
        interactor.saveToken(token)
    }
}