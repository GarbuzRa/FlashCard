package com.example.flashcard.di

import android.content.Context
import com.example.flashcard.data.repository.RegisterUserRepositoryImpl
import com.example.flashcard.data.remote.ServerApi
import com.example.flashcard.data.repository.NotificationRepositoryImpl
import com.example.flashcard.domain.repository.NotificationRepository
import com.example.flashcard.domain.repository.RegisterUserRepository
import com.example.flashcard.domain.usecase.NotificationInteractor
import com.example.flashcard.domain.usecase.RegisterUserUseCase
import com.example.flashcard.presentation.viewmodel.NotificationViewModel
import com.example.flashcard.presentation.viewmodel.RegistrationViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Koin
    val appModule = module {
        single<ServerApi> {
        Retrofit.Builder()
            .baseUrl("https://fbgjfbtifqnspssbdzuz.supabase.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ServerApi::class.java)
            }

        single<RegisterUserRepository>{ RegisterUserRepositoryImpl(get()) }
        single<NotificationRepository> { NotificationRepositoryImpl(get()) }
        single { androidContext().getSharedPreferences("GLOBAL_SP", Context.MODE_PRIVATE) }

        factory {RegisterUserUseCase(get())}
        factory {NotificationInteractor(get())}

        viewModel{RegistrationViewModel(get())}
        viewModel{NotificationViewModel(get())}

}