package com.example.trackingapp.ui.ui

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trackingapp.databinding.FragmentIncomeBinding
import com.example.trackingapp.ui.adapters.GoalsAdapter
import com.example.trackingapp.ui.data.GoalItem


class IncomeFragment : Fragment() {

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
        val binding = FragmentIncomeBinding.inflate(layoutInflater, container, false)


        binding.rvGoals.layoutManager = LinearLayoutManager(requireContext())
        binding.rvGoals.adapter = GoalsAdapter(listItems)



        return binding.root


    }


}