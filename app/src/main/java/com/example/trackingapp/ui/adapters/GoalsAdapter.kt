package com.example.trackingapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trackingapp.databinding.GoalItemBinding
import com.example.trackingapp.ui.data.GoalItem

class GoalsAdapter(private val dataSet: MutableList<GoalItem>?) : RecyclerView.Adapter<GoalsAdapter.GoalViewHolder>() {

    inner class GoalViewHolder(val binding: GoalItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return dataSet?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        val binding = GoalItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GoalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        val currentGoalItem = dataSet?.get(position)

        holder.binding.apply {
            tvGoalName.text = currentGoalItem?.GoalCategory
            tvGoalPercentage.text = currentGoalItem?.GoalPercentage
        }
    }


}