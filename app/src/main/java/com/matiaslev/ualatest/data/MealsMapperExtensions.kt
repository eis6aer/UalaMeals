package com.matiaslev.ualatest.data

import com.matiaslev.ualatest.domain.MealData

fun List<MealRemote>.mapToMealLocal(): List<MealLocal> = map {
    MealLocal(it.idMeal, it.strMealThumb, it.strMeal, it.strCategory, it.strInstructions,
        it.strIngredient1 ?: "",
        it.strIngredient2?: "",
        it.strIngredient3 ?: "",
        it.strIngredient4 ?: "",
        it.strIngredient5 ?: "",
        it.strIngredient6 ?: "",
        it.strIngredient7 ?: "",
        it.strIngredient8 ?: "",
        it.strIngredient9 ?: "",
        it.strIngredient10 ?: "",
        it.strIngredient11 ?: "",
        it.strIngredient12 ?: "",
        it.strIngredient13 ?: "",
        it.strIngredient14 ?: "",
        it.strIngredient15 ?: "",
        it.strIngredient16 ?: "",
        it.strIngredient17 ?: "",
        it.strIngredient18 ?: "",
        it.strIngredient19 ?: "",
        it.strIngredient20 ?: ""
    )
}

fun List<MealLocal>.mapToMealData(): List<MealData> = map {
    MealData(it.idMeal, it.strMealThumb, it.strMeal, it.strCategory, it.strInstructions,
        it.strIngredient1 ?: "",
        it.strIngredient2?: "",
        it.strIngredient3 ?: "",
        it.strIngredient4 ?: "",
        it.strIngredient5 ?: "",
        it.strIngredient6 ?: "",
        it.strIngredient7 ?: "",
        it.strIngredient8 ?: "",
        it.strIngredient9 ?: "",
        it.strIngredient10 ?: "",
        it.strIngredient11 ?: "",
        it.strIngredient12 ?: "",
        it.strIngredient13 ?: "",
        it.strIngredient14 ?: "",
        it.strIngredient15 ?: "",
        it.strIngredient16 ?: "",
        it.strIngredient17 ?: "",
        it.strIngredient18 ?: "",
        it.strIngredient19 ?: "",
        it.strIngredient20 ?: ""
        )
}