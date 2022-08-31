package com.example.trackingapp.ui.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trackingapp.databinding.FragmentHomeBinding
import com.example.trackingapp.ui.adapters.GoalsAdapter
import com.example.trackingapp.ui.data.GoalItem


class HomeFragment : Fragment() {

    companion object {
        val listItems = mutableListOf(
            GoalItem("Home","100%"),
            GoalItem("Home","100%"),
            GoalItem("Home","100%"),
            GoalItem("Home","100%"),
            GoalItem("Home","100%"),
            GoalItem("Home","100%"),
            GoalItem("Home","100%"),
            GoalItem("Home","100%"),
            GoalItem("Home","100%"),
            GoalItem("Home","100%"),
            GoalItem("Home","100%"),
            GoalItem("Home","100%"),
            GoalItem("Home","100%"),
            GoalItem("Home","100%"),
            GoalItem("Home","100%"),
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(layoutInflater, container, false)


        binding.rvGoals.layoutManager = LinearLayoutManager(requireContext())
        binding.rvGoals.adapter = GoalsAdapter(listItems)


        val args = this.arguments

        val inputExpenseData = args?.getString("expenseData")

        binding.tvAmountExpense.text = inputExpenseData.toString()

        return binding.root


    }


}