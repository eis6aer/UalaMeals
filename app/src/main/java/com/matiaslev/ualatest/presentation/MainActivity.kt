package com.matiaslev.ualatest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.matiaslev.ualatest.R
import com.matiaslev.ualatest.domain.GetAllMealsUseCase
import com.matiaslev.ualatest.domain.GetAllMealsUseCaseImpl
import com.matiaslev.ualatest.domain.SearchMealsUseCase
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val getAllMealsUseCase by inject<GetAllMealsUseCase>()
    private val searchMealsUseCase by inject<SearchMealsUseCase>()
    private val viewModel  by viewModels<MealViewModel>() {
        MainViewModelFactory(getAllMealsUseCase, searchMealsUseCase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        viewModel.syncList()
    }
}
