package com.matiaslev.ualatest.di

import androidx.room.Room
import com.matiaslev.ualatest.data.Api
import com.matiaslev.ualatest.data.AppDatabase
import com.matiaslev.ualatest.data.MealMealRepositoryImpl
import com.matiaslev.ualatest.domain.*
import com.matiaslev.ualatest.presentation.UalaListAdapter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val ualaModule = module {
    single { Api() }

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "uala-test-database"
        ).build()
    }

    single { UalaListAdapter() }

    single<MealRepository> { MealMealRepositoryImpl(get(), get()) }

    single<GetAllMealsUseCase> { GetAllMealsUseCaseImpl(get()) }

    single<SearchMealsUseCase> { SearchMealsUseCaseImpl(get()) }
}