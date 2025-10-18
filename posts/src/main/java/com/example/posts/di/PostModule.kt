package com.example.posts.di

import com.example.database.AppDatabase
import com.example.database.dao.PostDao
import com.example.posts.features.data.PostInteractorImpl
import com.example.posts.features.data.api.PostApiService
import com.example.posts.features.data.mapper.PostMapper
import com.example.posts.features.domain.PostInteractor
import com.example.posts.features.presentation.PostListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val postModule = module {
    single<PostApiService> {
        get<Retrofit>().create(PostApiService::class.java)
    }
    single<PostDao> {
        get<AppDatabase>().postDao()
    }
    factory { PostMapper() }
    single<PostInteractor> { PostInteractorImpl(get(), get(), get()) }

    viewModel { (userId: Int) ->
        PostListViewModel(userId, get())
    }
}
