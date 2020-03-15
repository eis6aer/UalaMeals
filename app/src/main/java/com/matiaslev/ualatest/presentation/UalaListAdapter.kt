package com.matiaslev.ualatest.presentation

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matiaslev.ualatest.R
import com.matiaslev.ualatest.domain.MealData
import com.matiaslev.ualatest.domain.Meal
import com.matiaslev.ualatest.domain.MealError
import kotlinx.android.synthetic.main.uala_list_item.view.categoryTextView
import kotlinx.android.synthetic.main.uala_list_item.view.mealImageView
import kotlinx.android.synthetic.main.uala_list_item.view.nameTextView

class UalaListAdapter(
    var mealList: List<Meal> = listOf(),
    var listener: (Int, String) -> Unit = { _, _ -> }
) : RecyclerView.Adapter<UalaListAdapter.UalaViewHolder>() {

    class UalaViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int, transitionName: String, listener: (Int, String) -> Unit) {
            view.setOnClickListener { listener(position, transitionName) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UalaViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.uala_list_item, parent, false)
        return UalaViewHolder(item)
    }

    override fun getItemCount(): Int = mealList.size

    override fun onBindViewHolder(holder: UalaViewHolder, position: Int) {
        when (val meal = mealList[position]) {
            is MealData -> {
                holder.bind(position, meal.name, listener)
                holder.view.mealImageView.apply {
                    Glide.with(context)
                        .load(meal.image)
                        .into(holder.view.mealImageView)
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.view.nameTextView.transitionName = meal.name
                }
                holder.view.nameTextView.text = meal.name
                holder.view.categoryTextView.text = meal.category
            }
            is MealError -> {
                holder.view.nameTextView.text = "ups!"
                holder.view.categoryTextView.text = "ups!"
            }
        }
    }
}