package com.matiaslev.ualatest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.matiaslev.ualatest.domain.AddRandomMealUseCase
import com.matiaslev.ualatest.domain.GetAllMealsUseCase
import com.matiaslev.ualatest.domain.Meal
import com.matiaslev.ualatest.domain.GetAllMealsUseCaseImpl
import com.matiaslev.ualatest.domain.MealData
import com.matiaslev.ualatest.domain.MealError
import com.matiaslev.ualatest.domain.SearchMealsUseCase
import com.matiaslev.ualatest.domain.SearchMealsUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MealViewModel(
    private val getAllMealsUseCase: GetAllMealsUseCase,
    private val searchAllMealsUseCase: SearchMealsUseCase,
    private val addRandomMealUseCase: AddRandomMealUseCase
) : ViewModel() {

    private val liveData = MutableLiveData<List<Meal>>()
    private val bannerLiveData = MutableLiveData<Meal>()
    private var bannerJob: Job? = null
    var selected = 0

    fun syncList() {
        viewModelScope.launch(IO) {
            val meals = getAllMealsUseCase()

            withContext(Dispatchers.Main) {
                meals.fold(
                    {
                        liveData.value = listOf(it)
                    },
                    {
                        liveData.value = it
                    }
                )
            }
        }
    }

    fun searchMeals(searchName: String) {
        viewModelScope.launch(IO) {
            val meals = searchAllMealsUseCase(searchName)

            withContext(Dispatchers.Main) {
                meals.fold(
                    {
                        liveData.value = listOf(it)
                    },
                    {
                        liveData.value = it
                    }
                )
            }
        }
    }

    fun updateBanner(): LiveData<Meal> {
        bannerJob = viewModelScope.launch {
            repeat(100) {
                var randomMeal: Either<MealError, MealData> = Either.left(MealError)

                withContext(IO) {
                    randomMeal = addRandomMealUseCase()
                }

                withContext(Dispatchers.Main) {
                    randomMeal.fold(
                        {
                            bannerLiveData.value = it
                        },
                        {
                            bannerLiveData.value = it
                        }
                    )
                }

                delay(30000)
            }
        }

        return bannerLiveData
    }

    fun stopUpdatingBanner() {
        bannerJob?.cancel()
    }

    fun getObservable() = liveData
}

open class MainViewModelFactory(
    private val getAllMealsUseCase: GetAllMealsUseCase,
    private val searchAllMealsUseCase: SearchMealsUseCase,
    private val addRandomMealUseCase: AddRandomMealUseCase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MealViewModel(
            getAllMealsUseCase,
            searchAllMealsUseCase,
            addRandomMealUseCase
        ) as T
    }
}
