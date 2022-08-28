package com.example.trackingapp.ui.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trackingapp.databinding.FragmentExpenseBinding
import com.example.trackingapp.ui.adapters.GoalsAdapter


class ExpenseFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentExpenseBinding.inflate(layoutInflater,container,false)

        binding.rvGoals.layoutManager = LinearLayoutManager(activity)
        binding.rvGoals.adapter = GoalsAdapter(IncomeFragment.listItems)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}