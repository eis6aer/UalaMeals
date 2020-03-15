package com.matiaslev.ualatest.domain

import arrow.core.Either

interface SearchMealsUseCase {
    operator fun invoke(searchName: String): Either<MealError, List<MealData>>
}

class SearchMealsUseCaseImpl(
    private val mealRepository: MealRepository
) : SearchMealsUseCase {
    override fun invoke(searchName: String): Either<MealError, List<MealData>> {
        return mealRepository.searchMeals(searchName)
    }
}