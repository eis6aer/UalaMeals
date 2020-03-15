package com.matiaslev.ualatest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.matiaslev.ualatest.R
import com.matiaslev.ualatest.domain.AddRandomMealUseCase
import com.matiaslev.ualatest.domain.GetAllMealsUseCase
import com.matiaslev.ualatest.domain.MealData
import com.matiaslev.ualatest.domain.MealError
import com.matiaslev.ualatest.domain.SearchMealsUseCase
import kotlinx.android.synthetic.main.main_fragment.bannerImageView
import kotlinx.android.synthetic.main.main_fragment.searchEditText
import kotlinx.android.synthetic.main.main_fragment.swipe_refresh
import kotlinx.android.synthetic.main.main_fragment.uala_list
import kotlinx.android.synthetic.main.uala_list_item.mealImageView
import kotlinx.android.synthetic.main.uala_list_item.nameTextView
import kotlinx.android.synthetic.main.uala_list_item.view.mealImageView
import org.koin.android.ext.android.inject

class MealListFragment : Fragment() {

    private val getAllMealsUseCase by inject<GetAllMealsUseCase>()
    private val searchMealsUseCase by inject<SearchMealsUseCase>()
    private val addRandomMealUseCase by inject<AddRandomMealUseCase>()
    private val viewModel by activityViewModels<MealViewModel>() {
        MainViewModelFactory(getAllMealsUseCase, searchMealsUseCase, addRandomMealUseCase)
    }
    private val ualaListAdapter by inject<UalaListAdapter>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        swipe_refresh.setOnRefreshListener {
            viewModel.syncList()
            swipe_refresh.isRefreshing = false
        }

        uala_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ualaListAdapter
        }

        ualaListAdapter.listener = { position, transitionName ->
            viewModel.selected = position
            val extras = FragmentNavigatorExtras(nameTextView to transitionName)
            findNavController().navigate(
                R.id.action_mainFragment_to_secondaryFragment,
                null,
                null,
                extras)
        }

        searchEditText.doOnTextChanged { text, _, _, _ ->
            when {
                text == null -> { }
                text.isEmpty() -> viewModel.syncList()
                text.isNotEmpty() -> viewModel.searchMeals(text.toString())
            }
        }

        viewModel.getObservable().observe(viewLifecycleOwner, Observer { mealList ->
                when(val meal = mealList.first()) {
                    is MealError -> {
                        ualaListAdapter.mealList = listOf(meal)
                        ualaListAdapter.notifyDataSetChanged()
                    }
                    is MealData -> {
                        ualaListAdapter.mealList = mealList
                        ualaListAdapter.notifyDataSetChanged()
                    }
                }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateBanner().observe(viewLifecycleOwner, Observer { bannerMeal ->
            when(bannerMeal) {
                is MealError -> {
                    bannerImageView.visibility = View.INVISIBLE
                }
                is MealData -> {
                    bannerImageView.visibility = View.VISIBLE
                    context?.run {
                        Glide.with(this)
                            .load(bannerMeal.image)
                            .into(bannerImageView)
                    }
                }
            }
        })
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopUpdatingBanner()
    }
}
