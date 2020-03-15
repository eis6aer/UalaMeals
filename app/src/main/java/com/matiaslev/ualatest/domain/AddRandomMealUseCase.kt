package com.matiaslev.ualatest.domain

import arrow.core.Either

interface AddRandomMealUseCase {
    operator fun invoke(): Either<MealError, MealData>
}

class AddRandomMealUseCaseImpl(
    private val mealRepository: MealRepository
) : AddRandomMealUseCase {
    override fun invoke(): Either<MealError, MealData> {
        return mealRepository.addRandomMeal()
    }
}