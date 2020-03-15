package com.matiaslev.ualatest.data

import android.util.Log
import arrow.core.Either
import com.github.kittinunf.fuel.core.extensions.cUrlString
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

class Api {
    fun getAllMeals(): Either<MealRemoteError, List<MealRemote>> {
        val (_, _, result) = "https://www.themealdb.com/api/json/v1/1/search.php?s="
            .httpGet()
            .also { Log.d(Api::class.java.canonicalName, it.cUrlString()) }
            .responseObject(MealRemote.ListDeserializer)



        return when (result) {
            is Result.Success -> {
                Either.right(result.value.meals)
            }
            is Result.Failure -> {
                Either.left(MealRemoteError)
            }
        }
    }

    fun getRandomMeal(): Either<MealRemoteError, List<MealRemote>> {
        val (_, _, result) = "https://www.themealdb.com/api/json/v1/1/random.php"
            .httpGet()
            .also { Log.d(Api::class.java.canonicalName, it.cUrlString()) }
            .responseObject(MealRemote.ListDeserializer)



        return when (result) {
            is Result.Success -> {
                Either.right(result.value.meals)
            }
            is Result.Failure -> {
                Either.left(MealRemoteError)
            }
        }
    }
}