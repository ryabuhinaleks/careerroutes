package com.example.users.di

import com.example.users.features.users.data.UserInteractorImpl
import com.example.users.features.users.data.api.UserApiService
import com.example.users.features.users.data.mapper.UserMapper
import com.example.users.features.users.domain.UserInteractor
import com.example.users.features.users.presentation.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val userModule = module {
    single<UserApiService> {
        get<Retrofit>().create(UserApiService::class.java)
    }
    factory { UserMapper() }
    single<UserInteractor> { UserInteractorImpl(get(), get()) }

    viewModel { UserListViewModel(get()) }
}
