package com.example.trackingapp.ui.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trackingapp.R
import com.example.trackingapp.databinding.FragmentHomeBinding
import com.example.trackingapp.ui.adapters.GoalsAdapter
import com.example.trackingapp.ui.data.GoalItem


class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        if (arguments == null) {
            binding.tvAmountExpense.text = "$00.0"
        } else  {
            binding.tvAmountExpense.text =  "$" + arguments?.getString("expenseText") + ".0"
        }



        binding.AddIncomeCard.setOnClickListener{

            findNavController().navigate(R.id.action_homeFragment_to_addIncomeFragment)

        }


        return binding.root


    }





}