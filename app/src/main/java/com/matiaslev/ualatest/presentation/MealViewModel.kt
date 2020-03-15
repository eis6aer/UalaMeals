package com.matiaslev.ualatest.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.matiaslev.ualatest.domain.GetAllMealsUseCase
import com.matiaslev.ualatest.domain.Meal
import com.matiaslev.ualatest.domain.GetAllMealsUseCaseImpl
import com.matiaslev.ualatest.domain.SearchMealsUseCase
import com.matiaslev.ualatest.domain.SearchMealsUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MealViewModel(
    private val getAllMealsUseCase: GetAllMealsUseCase,
    private val searchAllMealsUseCase: SearchMealsUseCase
) : ViewModel() {

    private val liveData = MutableLiveData<List<Meal>>()
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

    fun getObservable() = liveData
}

open class MainViewModelFactory(
    private val getAllMealsUseCase: GetAllMealsUseCase,
    private val searchAllMealsUseCase: SearchMealsUseCase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MealViewModel(
            getAllMealsUseCase,
            searchAllMealsUseCase
        ) as T
    }
}
