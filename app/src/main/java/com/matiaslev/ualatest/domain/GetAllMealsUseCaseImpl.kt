package com.matiaslev.ualatest.domain

import arrow.core.Either

interface GetAllMealsUseCase {
    operator fun invoke(): Either<MealError, List<MealData>>
}

class GetAllMealsUseCaseImpl(
    private val mealRepository: MealRepository
) : GetAllMealsUseCase {
    override fun invoke(): Either<MealError, List<MealData>> {
        return mealRepository.getAllMeals()
    }
}