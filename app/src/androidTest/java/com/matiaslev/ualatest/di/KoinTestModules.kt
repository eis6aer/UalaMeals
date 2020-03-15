package com.matiaslev.ualatest.di

import arrow.core.Either
import com.matiaslev.ualatest.UITestUtils.mealData
import com.matiaslev.ualatest.domain.AddRandomMealUseCase
import com.matiaslev.ualatest.domain.AddRandomMealUseCaseImpl
import com.matiaslev.ualatest.domain.GetAllMealsUseCase
import com.matiaslev.ualatest.domain.GetAllMealsUseCaseImpl
import com.matiaslev.ualatest.domain.MealError
import com.matiaslev.ualatest.domain.SearchMealsUseCase
import com.matiaslev.ualatest.domain.SearchMealsUseCaseImpl
import com.matiaslev.ualatest.presentation.UalaListAdapter
import io.mockk.every
import io.mockk.mockk
import org.koin.dsl.module

val testModule = module {
    single { UalaListAdapter() }

    single {
        val getAllMealsUseCaseImpl = mockk<GetAllMealsUseCase>()
        every { getAllMealsUseCaseImpl() } returns Either.Right(listOf(mealData))
        getAllMealsUseCaseImpl
    }

    single<SearchMealsUseCase> {
        val searchMealsUseCase = mockk<SearchMealsUseCaseImpl>()
        every { searchMealsUseCase("something") } returns Either.left(MealError)
        searchMealsUseCase
    }

    single<AddRandomMealUseCase> {
        val addRandomMealUseCase = mockk<AddRandomMealUseCaseImpl>()
        every { addRandomMealUseCase() } returns Either.left(MealError)
        addRandomMealUseCase
    }
}

val testModuleError = module {
    single { UalaListAdapter() }

    single<GetAllMealsUseCase> {
        val getAllMealsUseCaseImpl = mockk<GetAllMealsUseCaseImpl>()
        every { getAllMealsUseCaseImpl() } returns Either.left(MealError)
        getAllMealsUseCaseImpl
    }

    single<SearchMealsUseCase> {
        val searchMealsUseCase = mockk<SearchMealsUseCaseImpl>()
        every { searchMealsUseCase("something") } returns Either.left(MealError)
        searchMealsUseCase
    }

    single<AddRandomMealUseCase> {
        val addRandomMealUseCase = mockk<AddRandomMealUseCaseImpl>()
        every { addRandomMealUseCase() } returns Either.left(MealError)
        addRandomMealUseCase
    }
}