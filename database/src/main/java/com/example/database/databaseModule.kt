package com.example.database

import org.koin.dsl.module

val databaseModule = module {
    single<AppDatabase> {
        AppDatabase.getInstance(get())
    }
}