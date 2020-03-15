package com.matiaslev.ualatest.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.transition.TransitionInflater
import com.matiaslev.ualatest.R
import com.matiaslev.ualatest.domain.AddRandomMealUseCase
import com.matiaslev.ualatest.domain.GetAllMealsUseCase
import com.matiaslev.ualatest.domain.MealData
import com.matiaslev.ualatest.domain.MealError
import com.matiaslev.ualatest.domain.SearchMealsUseCase
import kotlinx.android.synthetic.main.secondary_fragment.ingredientsTextView
import kotlinx.android.synthetic.main.secondary_fragment.instructionsTextView
import kotlinx.android.synthetic.main.secondary_fragment.nameTextView
import org.koin.android.ext.android.inject

class MealDetailFragment : Fragment() {

    private val getAllMealsUseCase by inject<GetAllMealsUseCase>()
    private val searchMealsUseCase by inject<SearchMealsUseCase>()
    private val addRandomMealUseCase by inject<AddRandomMealUseCase>()
    private val viewModel by activityViewModels<MealViewModel>() {
        MainViewModelFactory(getAllMealsUseCase, searchMealsUseCase, addRandomMealUseCase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.secondary_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getObservable().observe(viewLifecycleOwner, Observer { mealList ->
            when(val meal = mealList[viewModel.selected]) {
                is MealError -> {
                    nameTextView.text = "ups!"
                    instructionsTextView.text = "ups!"
                }
                is MealData -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        nameTextView.transitionName = meal.name
                    }
                    nameTextView.text = meal.name
                    instructionsTextView.text = meal.instructions
                    ingredientsTextView.text = meal.formattedIngredients
                }
            }
        })
    }
}