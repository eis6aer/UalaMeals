package com.matiaslev.ualatest.di

import arrow.core.Either
import com.matiaslev.ualatest.domain.MealData
import com.matiaslev.ualatest.domain.MealError
import com.matiaslev.ualatest.domain.GetAllMealsUseCaseImpl
import com.matiaslev.ualatest.presentation.UalaListAdapter
import io.mockk.every
import io.mockk.mockk
import org.koin.dsl.module

val testModule = module {
    single { UalaListAdapter() }

    single {
        val useCaseListImpl = mockk<GetAllMealsUseCaseImpl>()
        every { useCaseListImpl() } returns Either.Right(listOf(MealData))
        useCaseListImpl
    }
}

val testModuleError = module {
    single { UalaListAdapter() }

    single {
        val useCaseListImpl = mockk<GetAllMealsUseCaseImpl>()
        every { useCaseListImpl() } returns Either.left(MealError)
        useCaseListImpl
    }
}