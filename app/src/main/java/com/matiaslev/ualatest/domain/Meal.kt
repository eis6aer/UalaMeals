package com.matiaslev.ualatest.domain

sealed class Meal

data class MealData(
    val id: String,
    val image: String,
    val name: String,
    val category: String,
    val instructions: String,
    val ingredient1: String,
    val ingredient2: String,
    val ingredient3: String,
    val ingredient4: String,
    val ingredient5: String,
    val ingredient6: String,
    val ingredient7: String,
    val ingredient8: String,
    val ingredient9: String,
    val ingredient10: String,
    val ingredient11: String,
    val ingredient12: String,
    val ingredient13: String,
    val ingredient14: String,
    val ingredient15: String,
    val ingredient16: String,
    val ingredient17: String,
    val ingredient18: String,
    val ingredient19: String,
    val ingredient20: String
) : Meal() {
    val formattedIngredients: String
        get() {
            var formatted = ""
            formatted = if (ingredient1.isNotEmpty()) formatted.plus("\n" + ingredient1) else formatted
            formatted = if (ingredient2.isNotEmpty()) formatted.plus("\n" + ingredient2) else formatted
            formatted = if (ingredient3.isNotEmpty()) formatted.plus("\n" + ingredient3) else formatted
            formatted = if (ingredient4.isNotEmpty()) formatted.plus("\n" + ingredient4) else formatted
            formatted = if (ingredient5.isNotEmpty()) formatted.plus("\n" + ingredient5) else formatted
            formatted = if (ingredient6.isNotEmpty()) formatted.plus("\n" + ingredient6) else formatted
            formatted = if (ingredient7.isNotEmpty()) formatted.plus("\n" + ingredient7) else formatted
            formatted = if (ingredient8.isNotEmpty()) formatted.plus("\n" + ingredient8) else formatted
            formatted = if (ingredient9.isNotEmpty()) formatted.plus("\n" + ingredient9) else formatted
            formatted = if (ingredient10.isNotEmpty()) formatted.plus("\n" + ingredient10) else formatted
            formatted = if (ingredient11.isNotEmpty()) formatted.plus("\n" + ingredient11) else formatted
            formatted = if (ingredient12.isNotEmpty()) formatted.plus("\n" + ingredient12) else formatted
            formatted = if (ingredient13.isNotEmpty()) formatted.plus("\n" + ingredient13) else formatted
            formatted = if (ingredient14.isNotEmpty()) formatted.plus("\n" + ingredient14) else formatted
            formatted = if (ingredient15.isNotEmpty()) formatted.plus("\n" + ingredient15) else formatted
            formatted = if (ingredient16.isNotEmpty()) formatted.plus("\n" + ingredient16) else formatted
            formatted = if (ingredient17.isNotEmpty()) formatted.plus("\n" + ingredient17) else formatted
            formatted = if (ingredient18.isNotEmpty()) formatted.plus("\n" + ingredient18) else formatted
            formatted = if (ingredient19.isNotEmpty()) formatted.plus("\n" + ingredient19) else formatted
            formatted = if (ingredient20.isNotEmpty()) formatted.plus("\n" + ingredient20) else formatted
            return formatted
        }
}

object MealError : Meal()