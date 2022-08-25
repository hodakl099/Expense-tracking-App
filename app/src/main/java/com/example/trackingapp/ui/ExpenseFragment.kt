package com.example.trackingapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trackingapp.R
import com.example.trackingapp.databinding.FragmentExpenseBinding
import com.example.trackingapp.databinding.GoalItemBinding
import com.example.trackingapp.ui.adapters.GoalsAdapter
import com.example.trackingapp.ui.data.GoalItem


class ExpenseFragment : Fragment() {

   private lateinit var binding: FragmentExpenseBinding

   private lateinit var goalAdapter: GoalsAdapter

   private var listOfGoals : MutableList<GoalItem>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = GoalItemBinding.inflate(layoutInflater,container,false)
        listOfGoals?.add(GoalItem("Home","100%"))
        goalAdapter = GoalsAdapter(listOfGoals)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}