package com.matiaslev.ualatest.domain

import arrow.core.Either

interface MealRepository {
    fun getAllMeals(): Either<MealError, List<MealData>>
    fun addRandomMeal(): Either<MealError, MealData>
    fun searchMeals(searchName: String): Either<MealError, List<MealData>>
}