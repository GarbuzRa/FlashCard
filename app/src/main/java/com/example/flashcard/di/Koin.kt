package com.example.flashcard.di

import com.example.flashcard.data.remote.RegisterUserRepositoryImpl
import com.example.flashcard.data.remote.ServerApi
import com.example.flashcard.domain.repository.RegisterUserRepository
import com.example.flashcard.domain.usecase.RegisterUserUseCase
import com.example.flashcard.presentation.viewmodel.RegistrationViewModel
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

    single<RegisterUserRepository>{RegisterUserRepositoryImpl(get())}

    factory {RegisterUserUseCase(get())}

    viewModel{RegistrationViewModel(get())}

}