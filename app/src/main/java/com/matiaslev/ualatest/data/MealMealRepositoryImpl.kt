package com.matiaslev.ualatest.data

import arrow.core.Either
import com.matiaslev.ualatest.domain.MealRepository
import com.matiaslev.ualatest.domain.MealData
import com.matiaslev.ualatest.domain.MealError

class MealMealRepositoryImpl(
    private val api: Api,
    private val database: AppDatabase
) : MealRepository {
    override fun getAllMeals(): Either<MealError, List<MealData>> {
        val mealsLocal = database.mealDao().getAll()
        return when  {
            mealsLocal.isEmpty() -> {
                api.getAllMeals()
                    .fold(
                        { bookRemoteError -> Either.left(MealError) },
                        { mealsRemote ->
                            val mealsLocal = mealsRemote.mapToMealLocal()
                            database.mealDao().insertAll(mealsLocal)
                            Either.right(mealsLocal.mapToMealData())
                        }
                    )
            }
            else -> Either.right(mealsLocal.mapToMealData())
        }
    }

    override fun searchMeals(searchName: String): Either<MealError, List<MealData>> {
        val mealsLocal = database.mealDao().search(searchName)
        return when {
            mealsLocal.isEmpty() -> Either.left(MealError)
            else -> Either.right(mealsLocal.mapToMealData())
        }
    }
}