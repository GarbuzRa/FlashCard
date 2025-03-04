package com.example.flashcard.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcard.data.remote.UserResponce
import com.example.flashcard.data.repository.RegisterUserRepositoryImpl
import com.example.flashcard.domain.model.UserDomain
import com.example.flashcard.domain.model.UserResponceDomain
import com.example.flashcard.domain.repository.RegisterUserRepository
import com.example.flashcard.domain.usecase.RegisterUserUseCase
import kotlinx.coroutines.launch

class RegistrationViewModel(val useCase: RegisterUserUseCase): ViewModel() {
    private val _responce = MutableLiveData<Result<UserResponceDomain>>()
    val responce: LiveData<Result<UserResponceDomain>> = _responce

    fun registerUser(user:UserDomain) {
        viewModelScope.launch {
            try {
                useCase(user).collect{ result ->
                    _responce.value = result
                }
            } catch (e:Exception) {
                _responce.value = Result.failure(e)
            }
        }
    }
}